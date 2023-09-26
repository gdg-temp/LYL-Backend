package GDG.backend.global.oauth.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GoogleToken(
        String accessToken,
        int expiresIn,
        String scope,
        String tokenType,
        String idToken
) {
}
