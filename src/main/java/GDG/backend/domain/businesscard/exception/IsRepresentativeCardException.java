package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class IsRepresentativeCardException extends CardException {

    public final static CardException EXCEPTION = new IsRepresentativeCardException();

    private IsRepresentativeCardException() {
        super(ErrorCode.IS_REPRESENTATIVE_CARD);
    }
}
