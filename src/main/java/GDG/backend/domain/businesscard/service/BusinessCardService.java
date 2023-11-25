package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.exception.BusinessCardNotFoundException;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeProfileRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.reason.domain.Reason;
import GDG.backend.domain.reason.exception.ReasonsExceededException;
import GDG.backend.domain.reason.service.ReasonServiceUtils;
import GDG.backend.domain.user.domain.User;
import GDG.backend.global.utils.security.SecurityUtils;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessCardService implements BusinessCardServiceUtils{

    private final BusinessCardRepository businessCardRepository;
    private final UserUtils userUtils;
    private final ReasonServiceUtils reasonServiceUtils;

    @Value("${base-secret}")
    private String baseSecret;

    // 명함 생성하기
    @Transactional
    public BusinessCardProfileResponse createBusinessCard(CreateBusinessCardRequest createBusinessCardRequest) {
        User currentUser = userUtils.getUserFromSecurityContext();
        if (4 <= createBusinessCardRequest.reasonTexts().size()) {
            throw ReasonsExceededException.EXCEPTION;
        }

        BusinessCard businessCard = BusinessCard.createBusinessCard(
                currentUser,
                createBusinessCardRequest.profileImage(),
                createBusinessCardRequest.name(),
                createBusinessCardRequest.email(),
                createBusinessCardRequest.introduction(),
                createBusinessCardRequest.styleTemplate(),
                createBusinessCardRequest.designTemplate(),
                createBusinessCardRequest.reasonTexts(),
                createBusinessCardRequest.companyName(),
                createBusinessCardRequest.position()
        );

        businessCardRepository.save(businessCard);
        String encodeId = encodeId(businessCard.getId());
        businessCard.registerEncodeId(encodeId);

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

        List<String> reasonTexts = card.getReason();

        User user = userUtils.getUserFromSecurityContext();
        String encodeId = card.getEncodeId();
        Boolean isMine = (user == card.getUser());

        return new BusinessCardProfileResponse(
                encodeId,
                card.getBusinessCardInfo(),
                isMine,
                reasonTexts,
                linkInfos
        );
    }


    // 해당 명함 조회하기
    public BusinessCardProfileResponse getBusinessCardProfile(String encodeId) {
        Long cardId = decodeId(encodeId);
        BusinessCard card = queryBusinessCard(cardId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isMine = TRUE;

        if (authentication.getPrincipal() == "anonymousUser" || userUtils.getUserFromSecurityContext() != card.getUser()) {
            isMine = FALSE;
        }

        List<LinkInfoVO> linkInfos;
        if (card.getLinks() != null) {
            linkInfos = card.getLinks().stream()
                    .map(Link::getLinkInfoVO)
                    .collect(Collectors.toList());
        } else {
            linkInfos = new ArrayList<>();
        }

        List<String> reasonTexts = card.getReason();

        return new BusinessCardProfileResponse(encodeId, card.getBusinessCardInfo(), isMine, reasonTexts, linkInfos);
    }

     // 명함 정보 수정하기
    public BusinessCardProfileResponse updateBusinessCardProfile(String encodeId, ChangeProfileRequest changeProfileRequest) {
        Long cardId = decodeId(encodeId);
        BusinessCard businessCard = validHost(cardId);

        businessCard.changeProfile(
                changeProfileRequest.profileImage(),
                changeProfileRequest.name(),
                changeProfileRequest.email(),
                changeProfileRequest.introduction(),
                changeProfileRequest.reasonTexts(),
                changeProfileRequest.styleTemplate(),
                changeProfileRequest.designTemplate(),
                changeProfileRequest.companyName(),
                changeProfileRequest.companyName()
        );

        return createProfileResponse(businessCard);
    }

    // 명함 삭제하기
    @Transactional
    public void deleteBusinessCard(String encodeId) {
        Long cardId = decodeId(encodeId);
        BusinessCard businessCard = validHost(cardId);

        businessCardRepository.delete(businessCard);
    }

    private String encodeId(Long id) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(baseSecret);

        return textEncryptor.encrypt(id.toString());
    }

    @Override
    public Long decodeId(String encodeId) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(baseSecret);
        return Long.parseLong(textEncryptor.decrypt(encodeId));
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
