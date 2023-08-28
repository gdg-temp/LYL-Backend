package GDG.backend.domain.template.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class TemplateNotFoundException extends CardException {

    public static final CardException EXCEPTION = new TemplateNotFoundException();


    private TemplateNotFoundException() {
        super(ErrorCode.Template_NOT_FOUND);
    }
}
