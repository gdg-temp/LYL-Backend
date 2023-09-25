package GDG.backend.global.oauth.naver.dto;

import GDG.backend.domain.oauth.domain.OauthMember;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static GDG.backend.domain.oauth.domain.OauthServerType.NAVER;

@JsonNaming(value = SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultcode,
        String message,
        Response response
) {
    public OauthMember toDomain() {
        return OauthMember.builder()
                .name(response.name)
                .email(response.email)
                .oauthServerType(NAVER)
                .build();
    }

    @JsonNaming(value = SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile
    ) {
    }
}
