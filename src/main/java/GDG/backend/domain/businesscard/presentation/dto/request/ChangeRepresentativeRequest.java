package GDG.backend.domain.businesscard.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChangeRepresentativeRequest(
        @Schema(description = "현재 대표 명함 Id")
        Long preBusinessCardId,
        @Schema(description = "대표 명함으로 변경할 명함 Id")
        Long changeBusinessCard
) {
}
