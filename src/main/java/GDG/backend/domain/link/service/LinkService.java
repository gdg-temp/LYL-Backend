package GDG.backend.domain.link.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.respository.LinkRepository;
import GDG.backend.domain.link.exception.LinkNotFoundException;
import GDG.backend.domain.link.presentation.dto.request.AddLinkRequest;
import GDG.backend.domain.link.presentation.dto.response.LinkProfileResponse;
import GDG.backend.global.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkService implements LinkServiceUtils{

    private final LinkRepository linkRepository;
    private final BusinessCardServiceUtils businessCardServiceUtils;

    // 링크 추가하기
    @Transactional
    public LinkProfileResponse addLink(Long cardId, AddLinkRequest addLinkRequest) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        BusinessCard businessCard = businessCardServiceUtils.queryBusinessCard(cardId);
        businessCard.validUserIsHost(currentUserId);

        Link link = Link.addLink(
                businessCard,
                addLinkRequest.linkType(),
                addLinkRequest.linkUrl(),
                addLinkRequest.linkText()
        );

        linkRepository.save(link);

        return new LinkProfileResponse(link.getLinkInfoVO());
    }
    @Override
    public Link queryLink(Long linkId) {
        return linkRepository.findById(linkId).orElseThrow(() -> LinkNotFoundException.EXCEPTION);
    }
}
