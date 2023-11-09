package GDG.backend.domain.user.presentation.dto.response;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.vo.UserInfoVO;

public record UserProfileResponse(
        Long id,
        String name,
        String email,
        boolean agreementRequired,
        boolean agreementAlarm,
        OauthServerType oauthServerType
) {
    public UserProfileResponse(UserInfoVO userInfo) {
        this(userInfo.id(), userInfo.name(), userInfo.email(), userInfo.agreementRequired()
                , userInfo.agreementAlarm(), userInfo.oauthServerType());
    }
}
