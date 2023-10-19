package GDG.backend.domain.link.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class LinkExceededException extends CardException {

    public static final CardException EXCEPTION = new LinkExceededException();

    private LinkExceededException() {
        super(ErrorCode.LINK_EXCEEDED);
    }
}
