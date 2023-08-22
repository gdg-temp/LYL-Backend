package GDG.backend.domain.user.service;

import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.repository.OauthMemberRepository;
import GDG.backend.domain.oauth.exception.OauthMemberNotFoundException;
import GDG.backend.domain.user.domain.User;
import GDG.backend.domain.user.domain.repository.RefreshTokenRepository;
import GDG.backend.domain.user.domain.repository.UserRepository;
import GDG.backend.domain.user.presentation.dto.request.SignUpUserRequest;
import GDG.backend.domain.user.presentation.dto.response.UserProfileResponse;
import GDG.backend.global.security.JwtTokenProvider;
import GDG.backend.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final OauthMemberRepository oauthMemberRepository;
    private final UserUtils utils;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public UserProfileResponse signUp(SignUpUserRequest signUpUserRequest) {
        OauthMember oauthMember = oauthMemberRepository.findByOauthId_OauthServerTypeAndEmail(signUpUserRequest.oauthServerType(), signUpUserRequest.email())
                .orElseThrow(() -> OauthMemberNotFoundException.EXCEPTION);

        User user = User.createUser(
                oauthMember.getName(),
                signUpUserRequest.phoneNum(),
                signUpUserRequest.email(),
                signUpUserRequest.birth(),
                signUpUserRequest.gender(),
                signUpUserRequest.oauthServerType()
        );

        userRepository.save(user);

        return new UserProfileResponse(user.getUserInfo());
    }
}
