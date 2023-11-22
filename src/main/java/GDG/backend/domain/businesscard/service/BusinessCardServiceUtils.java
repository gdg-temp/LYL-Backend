package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;

import java.util.List;

public interface BusinessCardServiceUtils {

    List<BusinessCardProfileResponse> getMyBusinessCardListResponse();
    BusinessCard queryBusinessCard(Long businessCardId);
    Long decodeId(String encodeId);
    BusinessCard validHost(Long cardId);
}
