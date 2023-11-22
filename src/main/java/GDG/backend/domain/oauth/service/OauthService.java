package GDG.backend.domain.oauth.service;

import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import GDG.backend.domain.oauth.client.OauthMemberClientComposite;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import GDG.backend.domain.oauth.presentation.dto.response.OauthLoginResponse;
import GDG.backend.domain.user.domain.RefreshToken;
import GDG.backend.domain.user.domain.User;
import GDG.backend.domain.user.domain.repository.RefreshTokenRepository;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.domain.user.presentation.dto.response.SignUpResponse;
import GDG.backend.global.exception.InvalidTokenException;
import GDG.backend.global.exception.RefreshTokenExpiredException;
import GDG.backend.global.security.JwtTokenProvider;
import GDG.backend.global.utils.user.UserUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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
    private final BusinessCardServiceUtils businessCardServiceUtils;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    // 추가
    @Transactional
    public OauthLoginResponse login(OauthServerType oauthServerType, String authCode, HttpServletResponse response) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthServerTypeAndEmail(oauthMember.getOauthServerType(), oauthMember.getEmail())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        Optional<User> savedUser = userRepository.findByOauthServerTypeAndEmail(saved.getOauthServerType(), saved.getEmail());

        if (!savedUser.isPresent()) {
            return new OauthLoginResponse(saved.getOauthMemberInfo(), TRUE);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(saved.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(saved.getId());

        RefreshToken userRefreshToken = refreshTokenRepository.findByUserId(savedUser.get().getId());
        userRefreshToken.updateRefreshToken(refreshToken);

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        return new OauthLoginResponse(saved.getOauthMemberInfo(), FALSE);
    }

    @Transactional
    public void tokenRefresh(HttpServletResponse response, String requestRefreshToken) {
        RefreshToken getRefreshToken = refreshTokenRepository.findByRefreshToken(requestRefreshToken).orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);

        Long userId = jwtTokenProvider.parseRefreshToken(requestRefreshToken);

        if (!userId.equals(getRefreshToken.getUserId())) {
            throw InvalidTokenException.EXCEPTION;
        }

        User user = userUtils.getUserById(userId);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        getRefreshToken.updateRefreshToken(refreshToken);
    }


}
