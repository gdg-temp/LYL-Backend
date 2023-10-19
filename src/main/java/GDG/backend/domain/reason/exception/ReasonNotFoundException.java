package GDG.backend.domain.reason.exception;

import GDG.backend.domain.link.exception.LinkNotFoundException;
import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class ReasonNotFoundException extends CardException {

    public static final CardException EXCEPTION = new ReasonNotFoundException();

    private ReasonNotFoundException() {
        super(ErrorCode.REASON_NOT_FOUND);
    }
}
