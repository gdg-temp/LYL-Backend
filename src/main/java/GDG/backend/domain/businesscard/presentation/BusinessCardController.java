package GDG.backend.domain.businesscard.presentation;

import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.service.BusinessCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @PostMapping("/{id}")
    public BusinessCardProfileResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest, @PathVariable Long id) {
        return businessCardService.createBusinessCard(id, createBusinessCardRequest);
    }
}
