package GDG.backend.domain.businesscard.presentation;

import GDG.backend.domain.businesscard.presentation.dto.request.ChangeProfileRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.ChangeRepresentativeRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.CreateBusinessCardResponse;
import GDG.backend.domain.businesscard.presentation.dto.response.MyBusinessCardListResponse;
import GDG.backend.domain.businesscard.service.BusinessCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "명함", description = "명함 관련 API")
@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @Operation(summary = "명함 생성하기")
    @PostMapping()
    public CreateBusinessCardResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest) {
        return businessCardService.createBusinessCard(createBusinessCardRequest);
    }

    @Operation(summary = "해당 명함 조회하기")
    @GetMapping("/{cardId}")
    public BusinessCardProfileResponse getBusinessCardProfile(@Parameter(description = "명함 Id", in = PATH) @PathVariable Long cardId) {
        return businessCardService.getBusinessCardProfile(cardId);
    }

    @Operation(summary = "명함 목록 조회하기")
    @GetMapping("/list")
    public MyBusinessCardListResponse getMyBusinessCardList() {
        return businessCardService.getMyBusinessCardList();
    }

    @Operation(summary = "대표 명함 변경하기")
    @PostMapping("/change")
    public void changeRepresentative(@RequestBody @Valid ChangeRepresentativeRequest changeRepresentativeRequest) {
        businessCardService.changeRepresentative(changeRepresentativeRequest);
    }

    @Operation(summary = "명함 수정하기")
    @PatchMapping("/profile/{cardId}")
    public BusinessCardProfileResponse changeProfile(@RequestBody @Valid ChangeProfileRequest changeProfileRequest
            , @Parameter(description = "명함 Id", in = PATH) @PathVariable Long cardId) {
        return businessCardService.updateBusinessCardProfile(cardId, changeProfileRequest);
    }

    @Operation(summary = "명함 삭제하기")
    @DeleteMapping("/{cardId}")
    public void deleteBusinessCard(@Parameter(description = "명함 Id", in = PATH) @PathVariable Long cardId) {
        businessCardService.deleteBusinessCard(cardId);
    }
}
