package GDG.backend.domain.template.presentation;

import GDG.backend.domain.template.presentation.dto.request.AddTemplateRequest;
import GDG.backend.domain.template.presentation.dto.request.UpdateTemplateRequest;
import GDG.backend.domain.template.presentation.dto.response.TemplateProfileResponse;
import GDG.backend.domain.template.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "템플릿", description = "템플릿 관련 API")
@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @Operation(summary = "기본 템플릿 추가하기")
    @PostMapping()
    public TemplateProfileResponse addTemplate(@RequestBody @Valid AddTemplateRequest addTemplateRequest) {
        return templateService.addTemplate(addTemplateRequest);
    }

    @Operation(summary = "기본 템플릿 수정하기")
    @PatchMapping("/{templateId}")
    public TemplateProfileResponse updateTemplate(
            @Parameter(description = "템플릿 Id", in = PATH)
            @PathVariable Long templateId,
            @RequestBody @Valid UpdateTemplateRequest updateTemplateRequest) {
        return templateService.updateTemplate(templateId, updateTemplateRequest);
    }

    @Operation(summary = "기본 템플릿 삭제하기")
    @DeleteMapping("/{templateId}")
    public void deleteTemplate(@Parameter(description = "템플릿 Id", in = PATH) @PathVariable Long templateId) {
        templateService.deleteTemplate(templateId);
    }

    @Operation(summary = "해당 기본 템플릿 조회하기")
    @GetMapping("/{templateId}")
    public TemplateProfileResponse getTemplateProfile(@Parameter(description = "템플릿 Id", in = PATH) @PathVariable Long templateId) {
        return templateService.getTemplateProfile(templateId);
    }

    @Operation(summary = "기본 템플릿 모두 조회하기")
    @GetMapping
    public List<TemplateProfileResponse> getTemplateList() {
        return templateService.getTemplateList();
    }
}
