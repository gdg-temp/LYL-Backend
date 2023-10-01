package GDG.backend.domain.link.presentation;

import GDG.backend.domain.link.presentation.dto.request.AddLinkRequest;
import GDG.backend.domain.link.presentation.dto.request.UpdateLinkRequest;
import GDG.backend.domain.link.presentation.dto.response.LinkProfileResponse;
import GDG.backend.domain.link.service.LinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "링크", description = "명함의 링크 관련 API")
@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @Operation(summary = "링크 추가하기")
    @PostMapping("/{cardId}")
    public List<LinkProfileResponse> addLink(
            @Parameter(description = "명함 Id", in = PATH)
            @PathVariable Long cardId,
            @RequestBody @Valid List<AddLinkRequest> addLinkRequests) {
        return linkService.addLinks(cardId, addLinkRequests);
    }

    @Operation(summary = "링크 수정하기")
    @PatchMapping("/{linkId}")
    public LinkProfileResponse updateLink(
            @Parameter(description = "링크 Id", in = PATH)
            @PathVariable Long linkId,
            @RequestBody @Valid UpdateLinkRequest updateLinkRequest) {
        return linkService.updateLink(linkId, updateLinkRequest);
    }

    @Operation(summary = "링크 삭제하기")
    @DeleteMapping("/{linkId}")
    public void deleteLink(@Parameter(description = "링크 Id", in = PATH) @PathVariable Long linkId) {
        linkService.deleteLink(linkId);
    }

    @Operation(summary = "해당 링크 조회하기")
    @GetMapping("/{linkId}")
    public LinkProfileResponse getLinkProfile(@Parameter(description = "링크 Id", in = PATH) @PathVariable Long linkId) {
        return linkService.getLinkProfile(linkId);
    }
}
