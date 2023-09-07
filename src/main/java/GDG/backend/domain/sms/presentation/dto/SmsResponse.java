package GDG.backend.domain.sms.presentation.dto;

import java.time.LocalDateTime;

public record SmsResponse(
        String requestId,
        LocalDateTime requestTime,
        String statusCode,
        String statusName,
        String smsConfirmNum
) {
}
