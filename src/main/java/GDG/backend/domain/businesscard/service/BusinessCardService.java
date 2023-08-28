package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.exception.BusinessCardNotFoundException;
import GDG.backend.domain.businesscard.exception.MyBusinessCardListNotFound;
import GDG.backend.domain.businesscard.exception.NotRepresentativeException;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeRepresentativeRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.CreateBusinessCardResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.user.domain.User;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 명함 생성하기
    @Transactional
    public CreateBusinessCardResponse createBusinessCard(Long id, CreateBusinessCardRequest createBusinessCardRequest) {
//        User currentUser = userUtils.getUserFromSecurityContext();
        User currentUser = userUtils.getUserById(id);
        BusinessCard businessCard = BusinessCard.createBusinessCard(
                currentUser,
                createBusinessCardRequest.name(),
                createBusinessCardRequest.email(),
                createBusinessCardRequest.workType(),
                createBusinessCardRequest.job(),
                createBusinessCardRequest.position(),
                createBusinessCardRequest.companyName(),
                createBusinessCardRequest.companyAddress(),
                createBusinessCardRequest.birth(),
                createBusinessCardRequest.gender()
        );

        businessCardRepository.save(businessCard);

        return new CreateBusinessCardResponse(businessCard.getBusinessCardInfo());
    }

    // 대표 명함 바꾸기
    @Transactional
    public void changeRepresentative(ChangeRepresentativeRequest changeRepresentativeRequest) {
        BusinessCard preBusinessCard = queryBusinessCard(changeRepresentativeRequest.preBusinessCardId());

        if (!preBusinessCard.getIsRepresentative()) {
            throw NotRepresentativeException.EXCEPTION;
        }

        preBusinessCard.changeRepresentative(FALSE);
        BusinessCard changeBusinessCard = queryBusinessCard(changeRepresentativeRequest.changeBusinessCard());
        changeBusinessCard.changeRepresentative(TRUE);
    }

    // 내 명함 리스트 조회하기
    public MyBusinessCardListResponse getMyBusinessCardList(Long userId) {
        User user = userUtils.getUserById(userId);
        List<BusinessCard> cards = businessCardRepository.findAllByUser(user);

        BusinessCard representative = cards.stream()
                .filter(BusinessCard::getIsRepresentative)
                .findFirst()
                .orElseThrow(() -> MyBusinessCardListNotFound.EXCEPTION);

        List<BusinessCard> otherCards = cards.stream()
                .filter(card -> !card.getIsRepresentative())
                .collect(Collectors.toList());

        BusinessCardProfileResponse repResponse = createProfileResponse(representative);
        List<BusinessCardProfileResponse> cardResponses = otherCards.stream()
                .map(this::createProfileResponse)
                .collect(Collectors.toList());

        return new MyBusinessCardListResponse(repResponse, cardResponses);
    }

    private BusinessCardProfileResponse createProfileResponse(BusinessCard card) {
        List<LinkInfoVO> linkInfos = card.getLinks().stream()
                .map(Link::getLinkInfoVO)
                .collect(Collectors.toList());

        return new BusinessCardProfileResponse(
                card.getBusinessCardInfo(),
                linkInfos,
                card.getTemplate().getTemplateInfo()
        );
    }


    // 해당 명함 조회하기
    public BusinessCardProfileResponse getBusinessCardProfile(Long cardId) {
        BusinessCard businessCard = queryBusinessCard(cardId);

        return new BusinessCardProfileResponse(businessCard.getBusinessCardInfo(),
                businessCard.getLinks().stream().map(l -> l.getLinkInfoVO()).collect(Collectors.toList()),
                businessCard.getTemplate().getTemplateInfo());
    }

    @Override
    public BusinessCard queryBusinessCard(Long businessCardId) {
        return businessCardRepository.findById(businessCardId).orElseThrow(() -> BusinessCardNotFoundException.EXCEPTION);
    }
}
