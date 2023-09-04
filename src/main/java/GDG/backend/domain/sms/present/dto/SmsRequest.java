package GDG.backend.domain.sms.present.dto;

import java.util.List;

public record SmsRequest(
        String type,
        String contentType,
        String countryCode,
        String from,
        String content,
        List<MessageDto> messages
) {
}
