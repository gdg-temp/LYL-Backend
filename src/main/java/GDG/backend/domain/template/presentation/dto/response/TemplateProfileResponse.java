package GDG.backend.domain.template.presentation.dto.response;

import GDG.backend.domain.template.domain.vo.TemplateInfoVO;

public record TemplateProfileResponse(
        Long id,
        String templateUrl
) {
    public TemplateProfileResponse(TemplateInfoVO templateInfoVO) {
        this(templateInfoVO.id(), templateInfoVO.templateUrl());
    }
}
