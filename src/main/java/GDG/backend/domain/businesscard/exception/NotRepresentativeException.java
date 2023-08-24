package GDG.backend.domain.businesscard.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class NotRepresentativeException extends CardException {

    public static final CardException EXCEPTION = new NotRepresentativeException();

    private NotRepresentativeException() {
        super(ErrorCode.NOT_REPRESENTATIVE);
    }
}
