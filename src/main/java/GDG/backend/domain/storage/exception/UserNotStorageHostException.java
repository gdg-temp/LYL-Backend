package GDG.backend.domain.storage.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class UserNotStorageHostException extends CardException {

    public static final UserNotStorageHostException EXCEPTION = new UserNotStorageHostException();

    private UserNotStorageHostException() {
        super(ErrorCode.User_Not_STORAGE_Host);
    }
}
