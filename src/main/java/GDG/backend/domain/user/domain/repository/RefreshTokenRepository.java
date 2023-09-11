package GDG.backend.domain.user.domain.repository;

import GDG.backend.domain.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String token);

    Optional<RefreshToken> findByRefreshToken(String token);

    void deleteByUserId(Long userId);
    @Transactional
    void deleteByRefreshToken(String token);
}
