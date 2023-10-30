package GDG.backend.domain.businesscard.presentation.dto.request;

import GDG.backend.domain.link.domain.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.util.List;

public record CreateBusinessCardRequest(
        String name,
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String email,
        String introduction,
        String styleTemplate,
        String designTemplate,
        @Nullable
        String companyName,
        @Nullable
        String position,
        List<String> reasonTexts
) {
}
