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

    @PostMapping()
    public CreateBusinessCardResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest, @PathVariable Long userId) {
        return businessCardService.createBusinessCard(createBusinessCardRequest);
    }

    @GetMapping("/{cardId}")
    public BusinessCardProfileResponse getBusinessCardProfile(@PathVariable Long cardId) {
        return businessCardService.getBusinessCardProfile(cardId);
    }

    @GetMapping("/list")
    public MyBusinessCardListResponse getMyBusinessCardList() {
        return businessCardService.getMyBusinessCardList();
    }

    @PostMapping("/change")
    public void changeRepresentative(@RequestBody @Valid ChangeRepresentativeRequest changeRepresentativeRequest) {
        businessCardService.changeRepresentative(changeRepresentativeRequest);
    }

    @PatchMapping("/profile/{cardId}")
    public BusinessCardProfileResponse changeProfile(@RequestBody @Valid ChangeProfileRequest changeProfileRequest, @PathVariable Long cardId) {
        return businessCardService.updateBusinessCardProfile(cardId, changeProfileRequest);
    }

    @DeleteMapping("/{cardId}")
    public void deleteBusinessCard(@PathVariable Long cardId) {
        businessCardService.deleteBusinessCard(cardId);
    }
}
