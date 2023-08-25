package GDG.backend.domain.link.domain.respository;

import GDG.backend.domain.link.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
