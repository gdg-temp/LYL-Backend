package GDG.backend.global.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class UserNotFoundException extends CardException {

    public static final CardException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
