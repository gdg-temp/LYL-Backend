package GDG.backend.domain.sms.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class NotExistPhoneNumException extends CardException {

    public static final CardException EXCEPTION = new NotExistPhoneNumException();

    private NotExistPhoneNumException() {
        super(ErrorCode.NOT_EXIST_PHONE_NUM);
    }
}
