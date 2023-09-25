package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.exception.BusinessCardNotFoundException;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeProfileRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.user.domain.User;
import GDG.backend.global.utils.security.SecurityUtils;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static software.amazon.ion.impl.PrivateIonConstants.False;
import static software.amazon.ion.impl.PrivateIonConstants.True;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessCardService implements BusinessCardServiceUtils{

    private final BusinessCardRepository businessCardRepository;
    private final UserUtils userUtils;

    // 명함 생성하기
    @Transactional
    public BusinessCardProfileResponse createBusinessCard(CreateBusinessCardRequest createBusinessCardRequest) {
        User currentUser = userUtils.getUserFromSecurityContext();
        BusinessCard businessCard = BusinessCard.createBusinessCard(
                currentUser,
                createBusinessCardRequest.profileImage(),
                createBusinessCardRequest.name(),
                createBusinessCardRequest.email(),
                createBusinessCardRequest.introduction(),
                createBusinessCardRequest.mbti(),
                createBusinessCardRequest.template(),
                createBusinessCardRequest.companyName(),
                createBusinessCardRequest.position()
        );

        businessCardRepository.save(businessCard);

        return createProfileResponse(businessCard);
    }

    @Override
    public List<BusinessCardProfileResponse> getMyBusinessCardListResponse() {
        User user = userUtils.getUserFromSecurityContext();
        List<BusinessCard> cardList = businessCardRepository.findAllByUser(user);

        List<BusinessCardProfileResponse> cardProfileResponseList = cardList.stream()
                .map(this::createProfileResponse)
                .collect(Collectors.toList());

        return cardProfileResponseList;
    }

    private BusinessCardProfileResponse createProfileResponse(BusinessCard card) {
        List<LinkInfoVO> linkInfos;
        if (card.getLinks() != null) {
            linkInfos = card.getLinks().stream()
                    .map(Link::getLinkInfoVO)
                    .collect(Collectors.toList());
        } else {
            linkInfos = new ArrayList<>();
        }

        User user = userUtils.getUserFromSecurityContext();

        Boolean isMine = (user == card.getUser());


        return new BusinessCardProfileResponse(
                card.getBusinessCardInfo(),
                isMine,
                linkInfos
        );
    }


    // 해당 명함 조회하기
    public BusinessCardProfileResponse getBusinessCardProfile(Long cardId) {
        BusinessCard card = queryBusinessCard(cardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isMine = TRUE;
        if (userUtils.getUserFromSecurityContext() != card.getUser() || authentication instanceof AnonymousAuthenticationToken || authentication == null) {
            isMine = FALSE;
        }

//        Boolean isMine = TRUE;
//        if (userUtils.getUserFromSecurityContext() != card.getUser() || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
//            isMine = FALSE;
//        }

        List<LinkInfoVO> linkInfos;
        if (card.getLinks() != null) {
            linkInfos = card.getLinks().stream()
                    .map(Link::getLinkInfoVO)
                    .collect(Collectors.toList());
        } else {
            linkInfos = new ArrayList<>();
        }
        return new BusinessCardProfileResponse(card.getBusinessCardInfo(), isMine, linkInfos);
    }

     // 명함 정보 수정하기
    public BusinessCardProfileResponse updateBusinessCardProfile(Long cardId, ChangeProfileRequest changeProfileRequest) {
        BusinessCard businessCard = validHost(cardId);

        businessCard.changeProfile(
                changeProfileRequest.profileImage(),
                changeProfileRequest.name(),
                changeProfileRequest.email(),
                changeProfileRequest.introduction(),
                changeProfileRequest.mbti(),
                changeProfileRequest.template(),
                changeProfileRequest.companyName(),
                changeProfileRequest.companyName()
        );

        return createProfileResponse(businessCard);
    }

    // 명함 삭제하기
    @Transactional
    public void deleteBusinessCard(Long cardId) {
        BusinessCard businessCard = validHost(cardId);

        businessCardRepository.delete(businessCard);
    }

    @Override
    public BusinessCard validHost(Long cardId) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        BusinessCard businessCard = queryBusinessCard(cardId);
        businessCard.validUserIsHost(currentUserId);
        return businessCard;
    }

    @Override
    public BusinessCard queryBusinessCard(Long businessCardId) {
        return businessCardRepository.findById(businessCardId).orElseThrow(() -> BusinessCardNotFoundException.EXCEPTION);
    }
}
