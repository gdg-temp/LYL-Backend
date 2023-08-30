package GDG.backend.domain.oauth.presentation.dto.response;

import GDG.backend.domain.oauth.domain.vo.OauthMemberInfoVO;

public record AuthTokensResponse(
        String accessToken,
        String refreshToken,
        OauthMemberInfoVO oauthMemberInfoVO
) {
}
