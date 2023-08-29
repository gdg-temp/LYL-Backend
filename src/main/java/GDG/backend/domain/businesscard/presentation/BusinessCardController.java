package GDG.backend.domain.businesscard.presentation;

import GDG.backend.domain.businesscard.presentation.dto.request.ChangeProfileRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeRepresentativeRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.CreateBusinessCardResponse;
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
    public CreateBusinessCardResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest, @PathVariable Long userId) {
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

    @PostMapping("/change/{userId}")
    public void changeRepresentative(@RequestBody @Valid ChangeRepresentativeRequest changeRepresentativeRequest, @PathVariable Long userId) {
        businessCardService.changeRepresentative(userId, changeRepresentativeRequest);
    }

    @PatchMapping("/profile/{userId}/{cardId}")
    public BusinessCardProfileResponse changeProfile(@RequestBody @Valid ChangeProfileRequest changeProfileRequest, @PathVariable Long userId, @PathVariable Long cardId) {
        return businessCardService.updateBusinessCardProfile(userId, cardId, changeProfileRequest);
    }

    @DeleteMapping("/{userId}/{cardId}")
    public void deleteBusinessCard(@PathVariable Long userId, @PathVariable Long cardId) {
        businessCardService.deleteBusinessCard(userId, cardId);
    }
}
