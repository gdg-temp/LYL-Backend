package GDG.backend.domain.template.domain;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.template.domain.vo.TemplateInfoVO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Template {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "template_id")
    private Long id;

    private String templateUrl;

    @Builder
    public Template(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public static Template addTemplate(String templateUrl) {
        return builder()
                .templateUrl(templateUrl)
                .build();
    }

    public TemplateInfoVO getTemplateInfo() {
        return new TemplateInfoVO(
                id,
                templateUrl
        );
    }

    public void updateTemplate(String templateUrl) {
        this.templateUrl = templateUrl;
    }
}
