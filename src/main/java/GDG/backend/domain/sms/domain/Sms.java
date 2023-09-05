package GDG.backend.domain.sms.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.Boolean.FALSE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Sms {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "sms_id")
    private Long id;

    private String toPhoneNum;
    private String smsKey;
    private Boolean isVerification;
    private LocalDateTime now;

    @Builder
    public Sms(String toPhoneNum, String smsKey, Boolean isVerification, LocalDateTime now) {
        this.toPhoneNum = toPhoneNum;
        this.smsKey = smsKey;
        this.isVerification = isVerification;
        this.now = now;
    }

    public static Sms createSms(String toPhoneNum, String smsKey) {
        return builder()
                .toPhoneNum(toPhoneNum)
                .smsKey(smsKey)
                .isVerification(FALSE)
                .now(LocalDateTime.now())
                .build();
    }

    public void changeVerification(Boolean status) {
        this.isVerification = status;
    }

    public void changeSmsKey(String smsKey) {
        this.smsKey = smsKey;
        this.now = LocalDateTime.now();
    }
}
