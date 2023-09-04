package GDG.backend.domain.sms.present;

import GDG.backend.domain.sms.present.dto.MessageDto;
import GDG.backend.domain.sms.present.dto.SmsResponse;
import GDG.backend.domain.sms.present.dto.SmsVerificationRequest;
import GDG.backend.domain.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public SmsResponse sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        SmsResponse responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }

    @PostMapping("/verification")
    public void verification(@RequestBody SmsVerificationRequest smsVerificationRequest) {
        smsService.verificationPhoneNum(smsVerificationRequest);
    }
}
