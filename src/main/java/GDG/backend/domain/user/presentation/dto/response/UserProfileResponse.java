package GDG.backend.domain.user.presentation.dto.response;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.Gender;
import GDG.backend.domain.user.domain.vo.UserInfoVO;

import java.time.LocalDate;

public record UserProfileResponse(
        Long id,
        String name,
        String phoneNum,
        String email,
        LocalDate birth,
        Gender gender,
        OauthServerType oauthServerType
) {
    public UserProfileResponse(UserInfoVO userInfo) {
        this(userInfo.id(), userInfo.name(), userInfo.phoneNum(), userInfo.email(), userInfo.birth(),
                userInfo.gender(), userInfo.oauthServerType());
    }
}
