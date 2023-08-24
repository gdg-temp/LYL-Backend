package GDG.backend.domain.businesscard.presentation.dto.response;

import java.util.List;

public record MyBusinessCardListResponse(
    BusinessCardProfileResponse representativeCard,
    List<BusinessCardProfileResponse> otherCards
) {
}
