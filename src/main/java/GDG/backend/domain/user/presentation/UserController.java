package GDG.backend.domain.user.presentation;

import GDG.backend.domain.user.presentation.dto.request.SignUpUserRequest;
import GDG.backend.domain.user.presentation.dto.response.TokenResponse;
import GDG.backend.domain.user.presentation.dto.response.UserProfileResponse;
import GDG.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저", description = "유저 관련 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SecurityRequirements
    @Operation(summary = "회원가입")
    @PostMapping("/signUp")
    public TokenResponse signUpUser(@RequestBody @Valid SignUpUserRequest signUpUserRequest) {
        return userService.signUp(signUpUserRequest);
    }
}
