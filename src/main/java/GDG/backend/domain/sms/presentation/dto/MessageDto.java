package GDG.backend.domain.sms.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MessageDto(
        @Schema(description = "전화 번호")
        String to
) {
}
