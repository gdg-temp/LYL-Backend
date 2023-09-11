package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
import GDG.backend.domain.user.domain.User;

public interface BusinessCardServiceUtils {

    MyBusinessCardListResponse getMyBusinessCardListResponse(User user);
    BusinessCard queryBusinessCard(Long businessCardId);

    BusinessCard validHost(Long cardId);
}
