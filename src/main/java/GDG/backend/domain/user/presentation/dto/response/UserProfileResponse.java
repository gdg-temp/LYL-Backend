package GDG.backend.domain.user.presentation.dto.response;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.Gender;
import GDG.backend.domain.user.domain.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

    private final Long id;
    private final String name;
    private final String phoneNum;
    private final String email;
    private final LocalDate birth;
    private final Gender gender;
    private final OauthServerType oauthServerType;

    public UserProfileResponse(UserInfoVO userInfoVO) {
        this.id = userInfoVO.id();
        this.name = userInfoVO.name();
        this.phoneNum = userInfoVO.phoneNum();
        this.email = userInfoVO.email();
        this.birth = userInfoVO.birth();
        this.gender = userInfoVO.gender();
        this.oauthServerType = userInfoVO.oauthServerType();
    }
}
