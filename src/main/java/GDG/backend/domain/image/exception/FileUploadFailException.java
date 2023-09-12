package GDG.backend.domain.image.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class FileUploadFailException extends CardException {

    public static final FileUploadFailException EXCEPTION = new FileUploadFailException();

    private FileUploadFailException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}
