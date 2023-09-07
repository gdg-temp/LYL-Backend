package GDG.backend.domain.sms.presentation;

import GDG.backend.domain.sms.presentation.dto.MessageDto;
import GDG.backend.domain.sms.presentation.dto.SmsResponse;
import GDG.backend.domain.sms.presentation.dto.SmsVerificationRequest;
import GDG.backend.domain.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Tag(name = "전화 번호 인증", description = "전화 번호 인증 관련 API")
@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @SecurityRequirements
    @Operation(summary = "인증 번호 받기")
    @PostMapping("/send")
    public SmsResponse sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponse responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }

    @SecurityRequirements
    @Operation(summary = "인증 번호 인증하기")
    @PostMapping("/verification")
    public void verification(@RequestBody SmsVerificationRequest smsVerificationRequest) {
        smsService.verificationPhoneNum(smsVerificationRequest);
    }
}
