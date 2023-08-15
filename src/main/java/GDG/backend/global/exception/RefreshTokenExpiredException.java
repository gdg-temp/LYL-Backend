package GDG.backend.global.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class RefreshTokenExpiredException extends CardException {

    public static final CardException EXCEPTION = new RefreshTokenExpiredException();

    private RefreshTokenExpiredException() {
        super(ErrorCode.REGISTER_EXPIRED_TOKEN);
    }
}
