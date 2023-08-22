package GDG.backend.domain.user.domain.vo;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;

public record UserInfoVO(
        Long id,
        String name,
        String phoneNum,
        String email,
        LocalDate birth,
        Gender gender,
        OauthServerType oauthServerType
) {
}
