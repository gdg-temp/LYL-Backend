package GDG.backend.domain.sms.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class IsNotVerificationException extends CardException {

    public static final CardException EXCEPTION = new IsNotVerificationException();

    private IsNotVerificationException() {
        super(ErrorCode.IS_NOT_VERIFICATION);
    }
}
