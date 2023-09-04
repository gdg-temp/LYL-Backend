package GDG.backend.domain.sms.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class AuthenticationTimeOutException extends CardException {

    public static final CardException EXCEPTION = new AuthenticationTimeOutException();

    private AuthenticationTimeOutException() {
        super(ErrorCode.AUTHENTICATION_TIME_OUT);
    }
}
