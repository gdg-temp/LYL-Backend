package GDG.backend.domain.businesscard.presentation.dto.request;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;

public record CreateBusinessCardRequest(
        String name,
        String email,
        WorkType workType,
        String job,
        String position,
        String companyName,
        String companyAddress,
        LocalDate birth,
        String templateURL,
        Gender gender
) {
}
