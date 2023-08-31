package GDG.backend.domain.businesscard.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;

public interface BusinessCardServiceUtils {

    BusinessCard queryBusinessCard(Long businessCardId);

    BusinessCard validHost(Long cardId);
}
