package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
import GDG.backend.domain.user.domain.User;

import java.util.List;

public interface BusinessCardServiceUtils {

    List<BusinessCardProfileResponse> getMyBusinessCardListResponse();
    BusinessCard queryBusinessCard(Long businessCardId);

    BusinessCard validHost(Long cardId);
}
