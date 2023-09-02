package GDG.backend.domain.storage.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class ExistStorageException extends CardException {

    public static final CardException EXCEPTION = new ExistStorageException();

    private ExistStorageException() {
        super(ErrorCode.EXIST_CARD_STORAGE);
    }
}
