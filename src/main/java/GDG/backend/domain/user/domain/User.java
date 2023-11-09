package GDG.backend.domain.user.domain;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.vo.UserInfoVO;
import GDG.backend.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String email;
    private boolean agreementRequired;
    private boolean agreementAlarm;

    @Enumerated(STRING)
    private OauthServerType oauthServerType;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<BusinessCard> businessCardList = new ArrayList<>();

    @Builder
    public User(String userName, String email, boolean agreementRequired, boolean agreementAlarm, OauthServerType oauthServerType) {
        this.userName = userName;
        this.email = email;
        this.agreementRequired = agreementRequired;
        this.agreementAlarm = agreementAlarm;
        this.oauthServerType = oauthServerType;
    }

    public static User createUser(String userName, String email, boolean agreementRequired, boolean agreementAlarm, OauthServerType oauthServerType) {
        return builder()
                .userName(userName)
                .email(email)
                .agreementRequired(agreementRequired)
                .agreementAlarm(agreementAlarm)
                .oauthServerType(oauthServerType)
                .build();
    }

    public UserInfoVO getUserInfo() {
        return new UserInfoVO(
                id,
                userName,
                email,
                agreementRequired,
                agreementAlarm,
                oauthServerType
        );
    }
}