package GDG.backend.domain.businesscard.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

public record ChangeProfileRequest(
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String name,
        String email,
        String introduction,
        String styleTemplate,
        String designTemplate,
        @Nullable
        String companyName,
        @Nullable
        String position
) {
}
