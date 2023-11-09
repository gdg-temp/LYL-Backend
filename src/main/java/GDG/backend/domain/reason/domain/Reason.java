package GDG.backend.domain.reason.domain;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.reason.domain.vo.ReasonInfoVO;
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
public class Reason {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reason_id")
    private Long id;

    private String text;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;

    @Builder
    public Reason(String text, BusinessCard businessCard) {
        this.text = text;
        this.businessCard = businessCard;
    }

    public static Reason addReason(String text, BusinessCard businessCard) {
        return builder()
                .text(text)
                .businessCard(businessCard)
                .build();
    }

    public ReasonInfoVO getReasonInfoVO() {
        return new ReasonInfoVO(text);
    }

    public void setBusinessCard(BusinessCard card) {
        this.businessCard = card;
        card.getReasons().add(this);
    }
}
