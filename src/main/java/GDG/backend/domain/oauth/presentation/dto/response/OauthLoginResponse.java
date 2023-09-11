package GDG.backend.domain.oauth.presentation.dto.response;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.domain.vo.OauthMemberInfoVO;

public record OauthLoginResponse(
        String email,
        String name,
        OauthServerType oauthServerType,
        Boolean isFirst
) {
    public OauthLoginResponse (OauthMemberInfoVO oauthMemberInfoVO, Boolean status) {
        this(oauthMemberInfoVO.email(), oauthMemberInfoVO.name(), oauthMemberInfoVO.oauthServerType(), status);
    }
}
