package GDG.backend.domain.user.service;

import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import GDG.backend.domain.sms.service.SmsUtils;
import GDG.backend.domain.user.domain.RefreshToken;
import GDG.backend.domain.user.domain.User;
import GDG.backend.domain.user.domain.repository.RefreshTokenRepository;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.domain.user.presentation.dto.request.SignUpUserRequest;
import GDG.backend.domain.user.presentation.dto.response.SignUpResponse;
import GDG.backend.domain.user.presentation.dto.response.UserProfileResponse;
import GDG.backend.global.security.JwtTokenProvider;
import GDG.backend.global.utils.security.SecurityUtils;
import GDG.backend.global.utils.user.UserUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.FALSE;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final OauthMemberRepository oauthMemberRepository;
    private final UserUtils userUtils;
    private final SmsUtils smsUtils;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public SignUpResponse signUp(SignUpUserRequest signUpUserRequest, HttpServletResponse response) {
        User user = User.createUser(
                signUpUserRequest.name(),
                signUpUserRequest.email(),
                signUpUserRequest.agreementRequired(),
                signUpUserRequest.agreementAlarm(),
                signUpUserRequest.oauthServerType()
        );

        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.getId()));
        return new SignUpResponse(user.getUserInfo(), FALSE);
    }

    public UserProfileResponse getUserProfile() {
        User user = userUtils.getUserFromSecurityContext();

        return new UserProfileResponse(user.getUserInfo());
    }

    @Transactional
    public void logout() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        refreshTokenRepository.deleteByUserId(currentUserId);
    }

    @Transactional
    public void userWithdraw() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        refreshTokenRepository.deleteByUserId(currentUserId);

        User currentUser = userUtils.getUserById(currentUserId);
        userRepository.delete(currentUser);
    }
}
