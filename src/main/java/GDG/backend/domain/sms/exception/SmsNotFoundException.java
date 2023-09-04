package GDG.backend.domain.sms.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class SmsNotFoundException extends CardException {

    public static final CardException EXCEPTION = new SmsNotFoundException();

    private SmsNotFoundException() {
        super(ErrorCode.SMS_NOT_FOUND);
    }
}
