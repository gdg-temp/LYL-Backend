package GDG.backend.domain.user.domain;

import GDG.backend.domain.oauth.domain.OauthServerType;
import GDG.backend.domain.user.domain.vo.UserInfoVO;
import GDG.backend.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String phoneNum;
    private String email;
    private LocalDate birth;

    @Enumerated(STRING)
    private Gender gender;

    @Enumerated(STRING)
    private OauthServerType oauthServerType;

    @Builder
    public User(String userName, String phoneNum, String email, LocalDate birth, Gender gender, OauthServerType oauthServerType) {
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.oauthServerType = oauthServerType;
    }

    public static User createUser(String userName, String phoneNum, String email, LocalDate birth, Gender gender, OauthServerType oauthServerType) {
        return builder()
                .userName(userName)
                .phoneNum(phoneNum)
                .email(email)
                .birth(birth)
                .gender(gender)
                .oauthServerType(oauthServerType)
                .build();
    }

    public UserInfoVO getUserInfo() {
        return new UserInfoVO(
                id,
                userName,
                phoneNum,
                email,
                birth,
                gender,
                oauthServerType
        );
    }
}