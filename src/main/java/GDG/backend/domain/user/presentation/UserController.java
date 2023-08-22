package GDG.backend.domain.user.presentation;

import GDG.backend.domain.user.presentation.dto.request.SignUpUserRequest;
import GDG.backend.domain.user.presentation.dto.response.UserProfileResponse;
import GDG.backend.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public UserProfileResponse signUpUser(@RequestBody @Valid SignUpUserRequest signUpUserRequest) {
        return userService.signUp(signUpUserRequest);
    }
}
