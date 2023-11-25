package GDG.backend.domain.reason.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.reason.domain.Reason;

public interface ReasonServiceUtils {

    Reason queryReason(Long reasonId);

    Reason createReason(BusinessCard card, String text);

    void deleteReason(Reason reason);
}
