package GDG.backend.domain.template.presentation.dto.response;

import GDG.backend.domain.template.domain.vo.TemplateInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;

public record TemplateProfileResponse(
        @Schema(description = "템플릿 Id")
        Long id,
        String templateUrl
) {
    public TemplateProfileResponse(TemplateInfoVO templateInfoVO) {
        this(templateInfoVO.id(), templateInfoVO.templateUrl());
    }
}
