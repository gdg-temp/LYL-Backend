package GDG.backend.domain.businesscard.presentation;

import GDG.backend.domain.businesscard.presentation.dto.request.ChangeProfileRequest;
import GDG.backend.domain.businesscard.presentation.dto.request.CreateBusinessCardRequest;
import GDG.backend.domain.businesscard.presentation.dto.response.BusinessCardProfileResponse;
import GDG.backend.domain.businesscard.service.BusinessCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "명함", description = "명함 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @Operation(summary = "명함 생성하기")
    @PostMapping("/generation")
    public BusinessCardProfileResponse createBusinessCard(@RequestBody @Valid CreateBusinessCardRequest createBusinessCardRequest) {
        return businessCardService.createBusinessCard(createBusinessCardRequest);
    }

    @Operation(summary = "해당 명함 조회하기")
    @GetMapping("/cards/{id}")
    public BusinessCardProfileResponse getBusinessCardProfile(@Parameter(description = "명함 Id", in = PATH) @PathVariable Long id) {
        return businessCardService.getBusinessCardProfile(id);
    }

    @Operation(summary = "명함 목록 조회하기")
    @GetMapping("/cards")
    public List<BusinessCardProfileResponse> getMyBusinessCardList() {
        return businessCardService.getMyBusinessCardListResponse();
    }

    @Operation(summary = "명함 수정하기")
    @PutMapping("/{id}/edit")
    public BusinessCardProfileResponse changeProfile(@RequestBody @Valid ChangeProfileRequest changeProfileRequest
            , @Parameter(description = "명함 Id", in = PATH) @PathVariable Long id) {
        return businessCardService.updateBusinessCardProfile(id, changeProfileRequest);
    }

    @Operation(summary = "명함 삭제하기")
    @DeleteMapping("/cards/{id}")
    public void deleteBusinessCard(@Parameter(description = "명함 Id", in = PATH) @PathVariable Long id) {
        businessCardService.deleteBusinessCard(id);
    }
}
