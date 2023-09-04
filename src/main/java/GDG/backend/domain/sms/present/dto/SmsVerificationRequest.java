package GDG.backend.domain.sms.present.dto;

public record SmsVerificationRequest(
        String phoneNum,
        String smsKey
) {
}
