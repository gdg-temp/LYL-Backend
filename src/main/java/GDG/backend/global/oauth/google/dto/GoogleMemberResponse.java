package GDG.backend.global.oauth.google.dto;

import GDG.backend.domain.oauth.domain.OauthMember;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static GDG.backend.domain.oauth.domain.OauthServerType.GOOGLE;

@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String id,
        String email,
        Boolean verifiedEmail,
        String name,
        String givenName,
        String familyName,
        String picture,
        String locale
) {
    public OauthMember toDomain() {
        return OauthMember.builder()
                .name(name)
                .email(email)
                .oauthServerType(GOOGLE)
                .build();
    }
}
