package GDG.backend.domain.oauth.domain;

import GDG.backend.domain.oauth.domain.OauthId;
import GDG.backend.domain.oauth.domain.vo.OauthMemberInfoVO;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthMember {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;
    private String name;
    private String email;

    public OauthMemberInfoVO getOauthMemberInfo() {
        return new OauthMemberInfoVO(
                email,
                oauthId.getOauthServerType()
        );
    }
}
