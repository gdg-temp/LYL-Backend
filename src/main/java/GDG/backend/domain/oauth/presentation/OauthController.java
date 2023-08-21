package GDG.backend.domain.oauth.presentation;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.oauth.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oauth")
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    // 추가
    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<Long> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam(value = "code") String code
    ) {
        Long login = oauthService.login(oauthServerType, code);
        return ResponseEntity.ok(login);
    }
}
