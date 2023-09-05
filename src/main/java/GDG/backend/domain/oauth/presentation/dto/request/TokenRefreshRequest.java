package GDG.backend.domain.oauth.presentation.dto.request;

public record TokenRefreshRequest(
        String refreshToken
) {
}
