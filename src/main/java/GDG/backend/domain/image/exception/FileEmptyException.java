package GDG.backend.domain.image.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class FileEmptyException extends CardException {

    public static final CardException EXCEPTION = new FileEmptyException();

    private FileEmptyException() {
        super(ErrorCode.FILE_EMPTY);
    }
}
