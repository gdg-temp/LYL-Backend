package GDG.backend.domain.oauth.presentation.dto.response;

import GDG.backend.domain.oauth.domain.vo.OauthMemberInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthTokensResponse(
        String accessToken,
        String refreshToken,
        @Schema(description = "로그인한 이력이 있는 유저 정보")
        OauthMemberInfoVO oauthMemberInfoVO
) {
}
