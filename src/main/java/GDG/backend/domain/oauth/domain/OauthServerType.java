package GDG.backend.domain.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Locale.ENGLISH;

@AllArgsConstructor
@Getter
public enum OauthServerType {
    KAKAO,
    GOOGLE,
    NAVER,
    APPLE;

    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(ENGLISH));
    }
}
