package GDG.backend.domain.businesscard.domain;

import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.businesscard.exception.UserNotHostException;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.template.domain.Template;
import GDG.backend.domain.user.domain.Gender;
import GDG.backend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
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
    
    private String name;
    private String email;

    @Enumerated(STRING)
    private WorkType workType;
    private String job;
    private String position;
    private String companyName;
    private String companyAddress;
    private LocalDate birth;

    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(mappedBy = "businessCard", cascade = ALL)
    private List<Link> links;

    private String templateURL;
    private Boolean isRepresentative;

    @Builder
    public BusinessCard(User user, String name, String email, WorkType workType, String job, String position, String companyName,
                        String companyAddress, LocalDate birth, Gender gender, String templateURL,  Boolean isRepresentative) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.workType = workType;
        this.job = job;
        this.position = position;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.birth = birth;
        this.templateURL = templateURL;
        this.gender = gender;
        this.isRepresentative = isRepresentative;
    }

    public static BusinessCard createBusinessCard(User user, String name, String email, WorkType workType, String job, String position, String companyName,
                                                  String companyAddress, LocalDate birth, String templateURL,  Gender gender) {
        return builder()
                .user(user)
                .name(name)
                .email(email)
                .workType(workType)
                .job(job)
                .position(position)
                .companyName(companyName)
                .companyAddress(companyAddress)
                .birth(birth)
                .gender(gender)
                .templateURL(templateURL)
                .isRepresentative(FALSE)
                .build();
    }

    public BusinessCardInfoVO getBusinessCardInfo() {
        return new BusinessCardInfoVO(
                id,
                name,
                email,
                workType,
                job,
                position,
                companyName,
                companyAddress,
                birth,
                gender,
                templateURL,
                isRepresentative
        );
    }

    public void changeRepresentative() {
        this.isRepresentative = !this.isRepresentative;
    }

    public void validUserIsHost(Long id) {
        if (!user.getId().equals(id)) {
            throw UserNotHostException.EXCEPTION;
        }
    }

    public void changeProfile(String name, String email, WorkType workType, String job, String position, String companyName,
                              String companyAddress) {
        this.name = name;
        this.email = email;
        this.workType = workType;
        this.job = job;
        this.position = position;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }
}
