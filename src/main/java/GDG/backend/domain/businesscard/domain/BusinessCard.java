package GDG.backend.domain.businesscard.domain;

import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.businesscard.exception.UserNotHostException;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.Boolean.FALSE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BusinessCard {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "business_card_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String profileImage;
    private String name;
    private String email;
    private String introduction;
    private String mbti;
    private String template;

    @OneToMany(mappedBy = "businessCard", cascade = ALL)
    private List<Link> links;

    private String companyName;
    private String position;

    @Builder
    public BusinessCard(User user, String profileImage, String name, String email, String introduction, String mbti,
                        String template, String companyName, String position) {
        this.user = user;
        this.profileImage = profileImage;
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.mbti = mbti;
        this.template = template;
        this.companyName = companyName;
        this.position = position;
    }

    public static BusinessCard createBusinessCard(User user, String profileImage, String name, String email, String introduction, String mbti,
                                                  String template, String companyName, String position) {
        return builder()
                .user(user)
                .profileImage(profileImage)
                .name(name)
                .email(email)
                .introduction(introduction)
                .mbti(mbti)
                .template(template)
                .companyName(companyName)
                .position(position)
                .build();
    }

    public BusinessCardInfoVO getBusinessCardInfo() {
        return new BusinessCardInfoVO(
                id,
                name,
                profileImage,
                email,
                introduction,
                mbti,
                template,
                companyName,
                position
        );
    }

    public void validUserIsHost(Long id) {
        if (!user.getId().equals(id)) {
            throw UserNotHostException.EXCEPTION;
        }
    }

    public void changeProfile(String profileImage, String name, String email, String introduction, String mbti,
                              String template, String companyName, String position) {
        this.profileImage = profileImage;
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.mbti = mbti;
        this.template = template;
        this.companyName = companyName;
        this.position = position;
    }
}
