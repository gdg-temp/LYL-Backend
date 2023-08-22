package GDG.backend.domain.oauth.exception;

import GDG.backend.global.error.exception.CardException;
import GDG.backend.global.error.exception.ErrorCode;

public class OauthMemberNotFoundException extends CardException {

    public static final CardException EXCEPTION = new OauthMemberNotFoundException();

    private OauthMemberNotFoundException() {
        super(ErrorCode.OAUTH_MEMBER_NOT_FOUND);
    }
}
