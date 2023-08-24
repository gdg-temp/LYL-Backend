package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class MyBusinessCardListNotFound extends CardException {

    public static final CardException EXCEPTION = new MyBusinessCardListNotFound();

    private MyBusinessCardListNotFound() {
        super(ErrorCode.MY_BUSINESS_CARD_LIST_NOT_FOUND);
    }
}
