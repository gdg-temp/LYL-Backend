package GDG.backend.domain.oauth.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class UserInfoNotFoundException extends CardException {

    public static final CardException EXCEPTION = new UserInfoNotFoundException();

    private UserInfoNotFoundException() {
        super(ErrorCode.USER_INFO_NOT_FOUND);
    }
}
