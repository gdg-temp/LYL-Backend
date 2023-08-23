package GDG.backend.domain.businesscard.domain.repository;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Long> {
}
