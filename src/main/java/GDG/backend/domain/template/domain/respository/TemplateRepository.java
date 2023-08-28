package GDG.backend.domain.template.domain.respository;

import GDG.backend.domain.template.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
