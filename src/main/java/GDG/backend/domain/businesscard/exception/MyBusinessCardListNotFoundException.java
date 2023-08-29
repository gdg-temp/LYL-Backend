package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class MyBusinessCardListNotFoundException extends CardException {

    public static final CardException EXCEPTION = new MyBusinessCardListNotFoundException();

    private MyBusinessCardListNotFoundException() {
        super(ErrorCode.MY_BUSINESS_CARD_LIST_NOT_FOUND);
    }
}
