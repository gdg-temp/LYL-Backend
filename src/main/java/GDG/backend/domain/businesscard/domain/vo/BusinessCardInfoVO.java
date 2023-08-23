package GDG.backend.domain.businesscard.domain.vo;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;

public record BusinessCardInfoVO(
        Long id,
        String name,
        String email,
        WorkType workType,
        String job,
        String position,
        String companyName,
        String companyAddress,
        LocalDate birth,
        Gender gender,
        Boolean isRepresentative
) {
}
