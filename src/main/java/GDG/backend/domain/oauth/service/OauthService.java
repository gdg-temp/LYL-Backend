package GDG.backend.domain.oauth.service;

import GDG.backend.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import GDG.backend.domain.oauth.client.OauthMemberClientComposite;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import GDG.backend.domain.oauth.exception.UserInfoNotFoundException;
import GDG.backend.domain.oauth.presentation.dto.response.AuthTokensResponse;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final UserRepository userRepository;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    // 추가
    public AuthTokensResponse login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        if (!userRepository.existsByEmail(saved.getEmail())) {
            return new AuthTokensResponse(null, null, saved.getOauthMemberInfo());
        }
        String accessToken = jwtTokenProvider.generateAccessToken(saved.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(saved.getId());
        return new AuthTokensResponse(accessToken, refreshToken, saved.getOauthMemberInfo());
    }
}
