package GDG.backend.domain.businesscard.presentation;

import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
import GDG.backend.domain.businesscard.service.BusinessCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @PostMapping("/{userId}")
    public BusinessCardProfileResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest, @PathVariable Long userId) {
        return businessCardService.createBusinessCard(userId, createBusinessCardRequest);
    }

    @GetMapping("/{cardId}")
    public BusinessCardProfileResponse getBusinessCardProfile(@PathVariable Long cardId) {
        return businessCardService.getBusinessCardProfile(cardId);
    }

    @GetMapping("/list/{userId}")
    public MyBusinessCardListResponse getMyBusinessCardList(@PathVariable Long userId) {
        return businessCardService.getMyBusinessCardList(userId);
    }
}
