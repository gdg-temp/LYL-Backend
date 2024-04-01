package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class CardExceededException extends CardException {

    public static final CardException EXCEPTION = new CardExceededException();

    private CardExceededException() {
        super(ErrorCode.CARD_NUM_EXCEEDED);
    }
}
