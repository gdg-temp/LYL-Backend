package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.domain.repository.BusinessCardRepository;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.user.domain.User;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessCardService {

    private final BusinessCardRepository businessCardRepository;
    private final UserUtils userUtils;

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
}
