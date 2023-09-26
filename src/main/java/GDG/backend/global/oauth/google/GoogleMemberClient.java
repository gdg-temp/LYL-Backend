package GDG.backend.global.oauth.google;

import GDG.backend.domain.oauth.client.OauthMemberClient;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.global.oauth.google.client.GoogleApiClient;
import GDG.backend.global.oauth.google.dto.GoogleMemberResponse;
import GDG.backend.global.oauth.google.dto.GoogleToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OauthMemberClient {

    private final GoogleApiClient googleApiClient;
    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public OauthMember fetch(String authCode) {
        GoogleToken tokenInfo = googleApiClient.fetchToken(tokenRequestParams(authCode));
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return googleMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authCode);
        params.add("client_id", googleOauthConfig.clientId());
        params.add("client_secret", googleOauthConfig.clientSecret());
        params.add("redirect_uri", googleOauthConfig.redirectUri());
        params.add("grant_type", "authorization_code");
        return params;
    }
}
