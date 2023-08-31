package GDG.backend.domain.link.presentation.dto.request;

import GDG.backend.domain.link.domain.LinkType;

public record UpdateLinkRequest(
        LinkType linkType,
        String linkUrl,
        String linkText
) {
}
