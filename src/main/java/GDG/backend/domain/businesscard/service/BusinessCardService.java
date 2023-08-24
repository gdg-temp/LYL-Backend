package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.exception.BusinessCardNotFoundException;
import GDG.backend.domain.businesscard.exception.MyBusinessCardListNotFound;
import GDG.backend.domain.businesscard.exception.NotRepresentativeException;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeRepresentativeRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
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
    public BusinessCardProfileResponse createBusinessCard(Long id, CreateBusinessCardRequest createBusinessCardRequest) {
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

        return new BusinessCardProfileResponse(businessCard.getBusinessCardInfo());
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
        BusinessCard representative = businessCardRepository.findByUserAndIsRepresentative(user, TRUE)
                .orElseThrow(() -> MyBusinessCardListNotFound.EXCEPTION);
        List<BusinessCard> cards = businessCardRepository.findAllByUserAndIsRepresentative(user, FALSE);

        BusinessCardProfileResponse repResponse = new BusinessCardProfileResponse(representative.getBusinessCardInfo());
        List<BusinessCardProfileResponse> cardResponses = cards.stream()
                .map(card -> new BusinessCardProfileResponse(card.getBusinessCardInfo()))
                .collect(Collectors.toList());


        return new MyBusinessCardListResponse(repResponse, cardResponses);
    }

    // 해당 명함 조회하기
    public BusinessCardProfileResponse getBusinessCardProfile(Long cardId) {
        BusinessCard businessCard = queryBusinessCard(cardId);

        return new BusinessCardProfileResponse(businessCard.getBusinessCardInfo());
    }

    @Override
    public BusinessCard queryBusinessCard(Long businessCardId) {
        return businessCardRepository.findById(businessCardId).orElseThrow(() -> BusinessCardNotFoundException.EXCEPTION);
    }
}
