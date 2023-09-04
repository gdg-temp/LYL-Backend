package GDG.backend.domain.sms.domain.repository;

import GDG.backend.domain.sms.domain.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<Sms, Long> {

    Optional<Sms> findByToPhoneNumAndSmsKey(String to, String smsKey);

    Optional<Sms> findByToPhoneNum(String to);
}
