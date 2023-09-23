package GDG.backend.domain.businesscard.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChangeProfileRequest(
        String name,
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String email,
        String introduction,
        String mbti,
        @Schema(description = "템플릿 URL")
        String template,
        String companyName,
        String position
) {
}
