package GDG.backend.domain.link.presentation;

import GDG.backend.domain.link.presentation.dto.request.AddLinkRequest;
import GDG.backend.domain.link.presentation.dto.request.UpdateLinkRequest;
import GDG.backend.domain.link.presentation.dto.response.LinkProfileResponse;
import GDG.backend.domain.link.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/{cardId}")
    public LinkProfileResponse addLink(@PathVariable Long cardId, @RequestBody @Valid AddLinkRequest addLinkRequest) {
        return linkService.addLink(cardId, addLinkRequest);
    }

    @PatchMapping("/{linkId}")
    public LinkProfileResponse updateLink(@PathVariable Long linkId, @RequestBody @Valid UpdateLinkRequest updateLinkRequest) {
        return linkService.updateLink(linkId, updateLinkRequest);
    }

    @DeleteMapping("/{linkId}")
    public void deleteLink(@PathVariable Long linkId) {
        linkService.deleteLink(linkId);
    }
}
