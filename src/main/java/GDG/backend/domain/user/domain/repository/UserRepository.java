package GDG.backend.domain.user.domain.repository;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByOauthServerTypeAndEmail(OauthServerType oauthServerType, String email);

    boolean existsByEmail(String email);
}
