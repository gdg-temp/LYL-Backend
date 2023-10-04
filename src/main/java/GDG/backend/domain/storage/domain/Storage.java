package GDG.backend.domain.storage.domain;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.exception.UserNotHostException;
import GDG.backend.domain.storage.exception.UserNotStorageHostException;
import GDG.backend.domain.user.domain.User;
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
public class Storage {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "storage_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;

    @Builder
    public Storage(User user, BusinessCard businessCard) {
        this.user = user;
        this.businessCard = businessCard;
    }

    public static Storage saveCard(User user, BusinessCard businessCard) {
        return builder()
                .user(user)
                .businessCard(businessCard)
                .build();
    }

    public void validStorage(Long id) {
        if (!user.getId().equals(id)) {
            throw UserNotStorageHostException.EXCEPTION;
        }
    }
}
