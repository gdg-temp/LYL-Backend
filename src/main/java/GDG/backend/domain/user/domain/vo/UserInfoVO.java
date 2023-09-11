package GDG.backend.domain.user.domain.vo;

import GDG.backend.domain.oauth.domain.OauthServerType;

public record UserInfoVO(
        Long id,
        String name,
        String email,
        OauthServerType oauthServerType
) {
}
