package GDG.backend.domain.link.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.link.domain.LinkType;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;

public record LinkProfileResponse(
        @Schema(description = "링크 Id")
        Long id,
        LinkType linkType,
        String linkUrl,
        String linkText
) {
    public LinkProfileResponse(LinkInfoVO linkInfoVO) {
        this(linkInfoVO.id(), linkInfoVO.linkType(), linkInfoVO.linkUrl(), linkInfoVO.linkText());
    }
}
