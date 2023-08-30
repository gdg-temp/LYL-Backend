package GDG.backend.domain.user.presentation.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
