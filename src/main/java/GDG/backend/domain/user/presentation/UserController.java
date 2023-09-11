package GDG.backend.domain.user.presentation;

import GDG.backend.domain.user.presentation.dto.request.SignUpUserRequest;
import GDG.backend.domain.user.presentation.dto.response.SignUpResponse;
import GDG.backend.domain.user.presentation.dto.response.UserProfileResponse;
import GDG.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SecurityRequirements
    @Operation(summary = "회원가입")
    @PostMapping("/signUp")
    public SignUpResponse signUpUser(@RequestBody @Valid SignUpUserRequest signUpUserRequest, HttpServletResponse response) {
        return userService.signUp(signUpUserRequest, response);
    }

    @Operation(summary = "회원 정보 조회하기")
    @GetMapping("/user")
    public UserProfileResponse getUserProfile() {
        return userService.getUserProfile();
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public void logout() {
        userService.logout();
    }
}
