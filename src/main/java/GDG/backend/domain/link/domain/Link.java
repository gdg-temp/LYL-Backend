package GDG.backend.domain.link.domain;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Link {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "link_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;

    @Enumerated(STRING)
    private LinkType linkType;
    private String linkUrl;
    private String linkText;

    @Builder
    public Link(BusinessCard businessCard, LinkType linkType, String linkUrl, String linkText) {
        this.businessCard = businessCard;
        this.linkType = linkType;
        this.linkUrl = linkUrl;
        this.linkText = linkText;
    }

    public static Link addLink(BusinessCard businessCard, LinkType linkType, String linkUrl, String linkText) {
        return builder()
                .businessCard(businessCard)
                .linkType(linkType)
                .linkUrl(linkUrl)
                .linkText(linkText)
                .build();
    }

    public LinkInfoVO getLinkInfoVO() {
        return new LinkInfoVO(
                id,
                linkType,
                linkUrl,
                linkText
        );
    }

    public void updateLink(LinkType linkType, String linkUrl, String linkText) {
        this.linkType = linkType;
        this.linkUrl = linkUrl;
        this.linkText = linkText;
    }
}
