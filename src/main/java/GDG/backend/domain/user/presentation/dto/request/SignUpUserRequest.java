package GDG.backend.domain.user.presentation.dto.request;

import GDG.backend.domain.oauth.domain.OauthServerType;

public record SignUpUserRequest(
        String name,
        String email,
        OauthServerType oauthServerType
) {
}
