package GDG.backend.domain.oauth.domain.repository;

import GDG.backend.domain.oauth.domain.OauthId;
import GDG.backend.domain.oauth.domain.OauthMember;
import GDG.backend.domain.oauth.domain.OauthServerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {

    Optional<OauthMember> findByOauthId(OauthId oauthId);

    Optional<OauthMember> findByOauthId_OauthServerTypeAndEmail(OauthServerType oauthServerType, String email);
}
