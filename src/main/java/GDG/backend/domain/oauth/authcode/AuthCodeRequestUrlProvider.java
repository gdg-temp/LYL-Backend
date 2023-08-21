package GDG.backend.domain.oauth.authcode;

import GDG.backend.domain.oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
