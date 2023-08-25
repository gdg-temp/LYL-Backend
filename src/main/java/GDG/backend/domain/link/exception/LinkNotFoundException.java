package GDG.backend.domain.link.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class LinkNotFoundException extends CardException {

    public static final CardException EXCEPTION = new LinkNotFoundException();

    private LinkNotFoundException() {
        super(ErrorCode.LINK_NOT_FOUND);
    }
}
