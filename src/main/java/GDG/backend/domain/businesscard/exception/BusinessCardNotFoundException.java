package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class BusinessCardNotFoundException extends CardException {

    public static final CardException EXCEPTION = new BusinessCardNotFoundException();

    private BusinessCardNotFoundException() {
        super(ErrorCode.BUSINESS_CARD_NOT_FOUND);
    }
}
