package GDG.backend.domain.user.presentation.dto.request;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;

public record SignUpUserRequest(
        String email,
        String phoneNum,
        Gender gender,
        LocalDate birth,
        OauthServerType oauthServerType
) {
}
