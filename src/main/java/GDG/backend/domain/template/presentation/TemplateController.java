package GDG.backend.domain.template.presentation;

import GDG.backend.domain.template.presentation.dto.request.AddTemplateRequest;
import GDG.backend.domain.template.presentation.dto.response.TemplateProfileResponse;
import GDG.backend.domain.template.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping("/{cardId}")
    public TemplateProfileResponse addTemplate(@PathVariable Long cardId, @RequestBody @Valid AddTemplateRequest addTemplateRequest) {
        return templateService.addTemplate(cardId, addTemplateRequest);
    }
}
