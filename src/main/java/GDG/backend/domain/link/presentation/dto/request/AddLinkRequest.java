package GDG.backend.domain.link.presentation.dto.request;

import GDG.backend.domain.link.domain.LinkType;

public record AddLinkRequest(
        LinkType linkType,
        String linkUrl,
        String linkText
) {
}
