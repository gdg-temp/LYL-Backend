package GDG.backend.domain.reason.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class ReasonsExceededException extends CardException {

    public static final CardException EXCEPTION = new ReasonsExceededException();

    private ReasonsExceededException() {
        super(ErrorCode.REASONS_EXCEEDED);
    }
}
