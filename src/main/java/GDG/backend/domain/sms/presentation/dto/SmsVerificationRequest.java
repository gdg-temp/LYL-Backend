package GDG.backend.domain.sms.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SmsVerificationRequest(
        String phoneNum,
        @Schema(description = "인증 번호")
        String smsKey
) {
}
