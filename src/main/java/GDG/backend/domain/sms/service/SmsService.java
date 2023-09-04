package GDG.backend.domain.sms.service;

import GDG.backend.domain.sms.domain.Sms;
import GDG.backend.domain.sms.domain.repository.SmsRepository;
import GDG.backend.domain.sms.exception.AuthenticationTimeOutException;
import GDG.backend.domain.sms.exception.IsNotVerificationException;
import GDG.backend.domain.sms.exception.SmsNotFoundException;
import GDG.backend.domain.sms.present.dto.MessageDto;
import GDG.backend.domain.sms.present.dto.SmsRequest;
import GDG.backend.domain.sms.present.dto.SmsResponse;
import GDG.backend.domain.sms.present.dto.SmsVerificationRequest;
import GDG.backend.global.config.SmsConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsService {

    private final SmsConfig smsConfig;
    private final SmsRepository smsRepository;

    @Transactional
    public SmsResponse sendSms(MessageDto messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        String time = Long.toString(System.currentTimeMillis());
        String smsKey = createSmsKey();
        List<MessageDto> messages = new ArrayList<>();
        // 보내는 사람에게 내용을 보냄.
        messages.add(new MessageDto(messageDto.to())); // content부분이 내용임

        // 전체 json에 대해 메시지를 만든다.
        SmsRequest smsRequestDto = new SmsRequest("SMS", "COMM", "82", smsConfig.senderPhone(), "[LYL] 인증번호[" + smsKey + "]를 입력해주세요.", messages);

        // 쌓아온 바디를 json 형태로 변환시켜준다.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);

        // 헤더에서 여러 설정값들을 잡아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", smsConfig.accessKey());

        // 제일 중요한 signature 서명하기.
        String sig = getSignature(time);
        System.out.println("sig -> " + sig);
        headers.set("x-ncp-apigw-signature-v2", sig);

        // 위에서 조립한 jsonBody와 헤더를 조립한다.
        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate를 통해 외부 api와 통신

        SmsResponse smsResponse = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+smsConfig.serviceId()+"/messages"), body, SmsResponse.class);

        Optional<Sms> optionalSms = smsRepository.findByToPhoneNum(messageDto.to());
        if (!optionalSms.isPresent()) {
            log.info("없어");
            Sms sms = Sms.createSms(messageDto.to(), smsKey);
            smsRepository.save(sms);
            log.info("저장");
        }
        else {
            Sms sms = optionalSms.get();
            sms.changeSmsKey(smsKey);
            sms.changeVerification(FALSE);
        }
        return smsResponse;
    }
    // 전달하고자 하는 데이터를 암호화해주는 작업
    public String getSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ smsConfig.serviceId()+"/messages";
        String accessKey = smsConfig.accessKey();
        String secretKey = smsConfig.secretKey();

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(time)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }
    // 5자리의 난수를 조합을 통해 인증코드 만들기
    public String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    // 핸드폰 번호 인증하기
    @Transactional
    public void verificationPhoneNum(SmsVerificationRequest smsVerificationRequest) {
        Optional<Sms> optionalSms = smsRepository.findByToPhoneNum(smsVerificationRequest.phoneNum());

        if (!optionalSms.isPresent()) {
            throw SmsNotFoundException.EXCEPTION;
        }

        Sms sms = optionalSms.get();

        if (!sms.getSmsKey().equals(smsVerificationRequest.smsKey())) {
            throw IsNotVerificationException.EXCEPTION;
        }

        if (sms.getNow().plusMinutes(5).isBefore(LocalDateTime.now())) {
            throw AuthenticationTimeOutException.EXCEPTION;
        }
        sms.changeVerification(TRUE);
    }

}
