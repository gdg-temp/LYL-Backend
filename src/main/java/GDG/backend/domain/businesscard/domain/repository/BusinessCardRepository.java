package GDG.backend.domain.businesscard.domain.repository;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Long> {

    boolean existsByUser(User user);
    List<BusinessCard> findAllByUser(User user);

    Optional<BusinessCard> findByEncodeId(String encodeId);
    Long countByUser(User user);
}
