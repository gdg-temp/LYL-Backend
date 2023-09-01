package GDG.backend.domain.template.presentation;

import GDG.backend.domain.template.presentation.dto.request.AddTemplateRequest;
import GDG.backend.domain.template.presentation.dto.request.UpdateTemplateRequest;
import GDG.backend.domain.template.presentation.dto.response.TemplateProfileResponse;
import GDG.backend.domain.template.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping()
    public TemplateProfileResponse addTemplate(@RequestBody @Valid AddTemplateRequest addTemplateRequest) {
        return templateService.addTemplate(addTemplateRequest);
    }

    @PatchMapping("/{templateId}")
    public TemplateProfileResponse updateTemplate(@PathVariable Long templateId, @RequestBody @Valid UpdateTemplateRequest updateTemplateRequest) {
        return templateService.updateTemplate(templateId, updateTemplateRequest);
    }

    @DeleteMapping("/{templateId}")
    public void deleteTemplate(@PathVariable Long templateId) {
        templateService.deleteTemplate(templateId);
    }

    @GetMapping("/{templateId}")
    public TemplateProfileResponse getTemplateProfile(@PathVariable Long templateId) {
        return templateService.getTemplateProfile(templateId);
    }

    @GetMapping
    public List<TemplateProfileResponse> getTemplateList() {
        return templateService.getTemplateList();
    }
}
