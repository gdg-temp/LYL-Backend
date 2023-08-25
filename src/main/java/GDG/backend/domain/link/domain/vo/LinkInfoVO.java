package GDG.backend.domain.link.domain.vo;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.link.domain.LinkType;

public record LinkInfoVO(
        Long id,
        LinkType linkType,
        String linkUrl,
        String linkText
) {
}
