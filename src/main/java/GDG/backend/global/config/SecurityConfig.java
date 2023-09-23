package GDG.backend.global.config;

import GDG.backend.global.security.JwtTokenFilter;
import GDG.backend.global.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    private static final String[] SwaggerPatterns = {
            "/swagger-ui/**", "/v3/api-docs/**",
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable().cors().and().csrf().disable();

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/sms/**").permitAll()
                        .requestMatchers("/api/login/{oauthServerType}").permitAll()
                        .requestMatchers("/api/cards/{id}").permitAll()
                        .requestMatchers("/api/refresh").permitAll()
                        .requestMatchers("/api/signUp").permitAll()
                        .requestMatchers("/api").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(SwaggerPatterns).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
