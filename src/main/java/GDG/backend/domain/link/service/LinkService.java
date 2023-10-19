package GDG.backend.domain.link.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.respository.LinkRepository;
import GDG.backend.domain.link.exception.LinkExceededException;
import GDG.backend.domain.link.exception.LinkNotFoundException;
import GDG.backend.domain.link.presentation.dto.request.AddLinkRequest;
import GDG.backend.domain.link.presentation.dto.request.UpdateLinkRequest;
import GDG.backend.domain.link.presentation.dto.response.LinkProfileResponse;
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
        BusinessCard businessCard = businessCardServiceUtils.validHost(cardId);

        if (4 <= linkRepository.countAllByBusinessCard(businessCard)) {
            throw LinkExceededException.EXCEPTION;
        }

        Link link = Link.addLink(
                businessCard,
                addLinkRequest.linkType(),
                addLinkRequest.linkUrl(),
                addLinkRequest.linkText()
        );

        linkRepository.save(link);

        return new LinkProfileResponse(link.getLinkInfoVO());
    }


    // 링크 수정하기
    @Transactional
    public LinkProfileResponse updateLink(Long linkId, UpdateLinkRequest updateLinkRequest) {
        Link link = queryLink(linkId);

        businessCardServiceUtils.validHost(link.getBusinessCard().getId());

        link.updateLink(updateLinkRequest.linkType(), updateLinkRequest.linkUrl(),updateLinkRequest.linkText());

        return new LinkProfileResponse(link.getLinkInfoVO());
    }

    // 링크 삭제하기
    @Transactional
    public void deleteLink(Long linkId) {
        Link link = queryLink(linkId);

        businessCardServiceUtils.validHost(link.getBusinessCard().getId());

        linkRepository.delete(link);
    }

    public LinkProfileResponse getLinkProfile(Long linkId) {
        Link link = queryLink(linkId);

        return new LinkProfileResponse(link.getLinkInfoVO());
    }
    @Override
    public Link queryLink(Long linkId) {
        return linkRepository.findById(linkId).orElseThrow(() -> LinkNotFoundException.EXCEPTION);
    }
}
