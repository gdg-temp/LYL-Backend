package GDG.backend.domain.storage.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class StorageNotFoundException extends CardException {

    public static final CardException EXCEPTION = new StorageNotFoundException();

    private StorageNotFoundException() {
        super(ErrorCode.CARD_STORAGE_NOT_FOUND);
    }
}
