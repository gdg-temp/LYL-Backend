package GDG.backend.domain.businesscard.presentation.dto.request;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.link.domain.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;

public record CreateBusinessCardRequest(
        String name,
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String email,
        String introduction,
        String mbti,
        @Schema(description = "템플릿 URL")
        String template,
        String companyName,
        String position,
        @Nullable
        List<Link> links
) {
}
