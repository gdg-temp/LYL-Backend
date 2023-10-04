package GDG.backend.domain.storage.domain.respository;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.storage.domain.Storage;
import GDG.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    List<Storage> findAllByUser(User user);

    boolean existsByUserAndBusinessCard(User user, BusinessCard businessCard);
    Optional<Storage> findByUserAndBusinessCard(User user, BusinessCard businessCard);
}
