package GDG.backend.domain.businesscard.domain.repository;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Long> {

    Optional<BusinessCard> findByUserAndIsRepresentative(User user, Boolean isRepresentative);
    List<BusinessCard> findAllByUserAndIsRepresentative(User user, Boolean isRepresentative);
}
