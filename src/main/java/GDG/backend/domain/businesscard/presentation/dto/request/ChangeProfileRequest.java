package GDG.backend.domain.businesscard.presentation.dto.request;

import GDG.backend.domain.businesscard.domain.WorkType;

public record ChangeProfileRequest(
        String name,
        String email,
        WorkType workType,
        String job,
        String position,
        String companyName,
        String companyAddress
) {
}
