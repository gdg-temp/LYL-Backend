package GDG.backend.domain.image.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class FileSizeException extends CardException {

    public static final CardException EXCEPTION = new FileSizeException();

    private FileSizeException() {
        super(ErrorCode.IMAGE_SIZE_EXTENSION);
    }
}
