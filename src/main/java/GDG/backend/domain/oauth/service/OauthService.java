package GDG.backend.domain.oauth.service;

import GDG.backend.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import GDG.backend.domain.oauth.client.OauthMemberClientComposite;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import GDG.backend.domain.oauth.exception.UserInfoNotFoundException;
import GDG.backend.domain.oauth.presentation.dto.response.AuthTokensResponse;
import GDG.backend.domain.user.domain.RefreshToken;
import GDG.backend.domain.user.domain.User;
import GDG.backend.domain.user.domain.repository.RefreshTokenRepository;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.domain.user.presentation.dto.response.TokenResponse;
import GDG.backend.global.exception.InvalidTokenException;
import GDG.backend.global.exception.RefreshTokenExpiredException;
import GDG.backend.global.security.JwtTokenProvider;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final UserRepository userRepository;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtils userUtils;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    // 추가
    public AuthTokensResponse login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        Optional<User> user = userRepository.findByEmail(saved.getEmail());
        if (!user.isPresent()) {
            return new AuthTokensResponse(null, null, saved.getOauthMemberInfo());
        }
        String accessToken = jwtTokenProvider.generateAccessToken(saved.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(saved.getId());

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.get().getId()));

        return new AuthTokensResponse(accessToken, refreshToken, saved.getOauthMemberInfo());
    }

    @Transactional
    public TokenResponse tokenRefresh(String requestRefreshToken) {
        RefreshToken getRefreshToken = refreshTokenRepository.findByRefreshToken(requestRefreshToken).orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);

        Long userId = jwtTokenProvider.parseRefreshToken(requestRefreshToken);

        if (!userId.equals(getRefreshToken.getUserId())) {
            throw InvalidTokenException.EXCEPTION;
        }

        User user = userUtils.getUserById(userId);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        getRefreshToken.updateRefreshToken(refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
