package GDG.backend.domain.storage.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class CardHostNotStorageException extends CardException {

    public static final CardException EXCEPTION = new CardHostNotStorageException();

    private CardHostNotStorageException() {
        super(ErrorCode.CARD_HOST_NOT_STORAGE);
    }
}
