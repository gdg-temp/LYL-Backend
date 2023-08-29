package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class UserNotHostException extends CardException {

    public final static CardException EXCEPTION = new UserNotHostException();

    private UserNotHostException() {
        super(ErrorCode.User_Not_Host);
    }
}
