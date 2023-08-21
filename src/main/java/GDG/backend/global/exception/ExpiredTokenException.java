package GDG.backend.global.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class ExpiredTokenException extends CardException {

    public static final CardException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
