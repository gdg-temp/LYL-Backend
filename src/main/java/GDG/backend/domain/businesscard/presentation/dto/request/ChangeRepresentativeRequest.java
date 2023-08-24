package GDG.backend.domain.businesscard.presentation.dto.request;

public record ChangeRepresentativeRequest(
        Long preBusinessCardId,
        Long changeBusinessCard
) {
}
