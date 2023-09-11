package GDG.backend.domain.businesscard.presentation.dto.request;

import GDG.backend.domain.businesscard.domain.WorkType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateBusinessCardRequest(
        String name,
        String email,
        WorkType workType,
        String job,
        String position,
        String companyName,
        String companyAddress,
        LocalDate birth,
        @Schema(description = "템플릿 이미지 URL")
        String templateURL
) {
}
