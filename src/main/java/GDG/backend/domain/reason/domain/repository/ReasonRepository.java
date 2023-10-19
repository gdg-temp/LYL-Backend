package GDG.backend.domain.reason.domain.repository;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.reason.domain.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReasonRepository extends JpaRepository<Reason, Long> {

    Long countAllByBusinessCard(BusinessCard card);
}
