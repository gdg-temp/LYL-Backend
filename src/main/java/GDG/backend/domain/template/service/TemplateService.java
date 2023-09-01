package GDG.backend.domain.template.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.template.domain.Template;
import GDG.backend.domain.template.domain.respository.TemplateRepository;
import GDG.backend.domain.template.domain.vo.TemplateInfoVO;
import GDG.backend.domain.template.exception.TemplateNotFoundException;
import GDG.backend.domain.template.presentation.dto.request.AddTemplateRequest;
import GDG.backend.domain.template.presentation.dto.request.UpdateTemplateRequest;
import GDG.backend.domain.template.presentation.dto.response.TemplateProfileResponse;
import GDG.backend.global.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final BusinessCardServiceUtils businessCardServiceUtils;

    @Transactional
    public TemplateProfileResponse addTemplate(AddTemplateRequest addTemplateRequest) {
        Template template = Template.addTemplate(addTemplateRequest.templateUrl());

        templateRepository.save(template);

        return new TemplateProfileResponse(template.getTemplateInfo());
    }

    @Transactional
    public TemplateProfileResponse updateTemplate(Long templateId, UpdateTemplateRequest updateTemplateRequest) {
        Template template = queryTemplate(templateId);
        template.updateTemplate(updateTemplateRequest.templateUrl());

        return new TemplateProfileResponse(template.getTemplateInfo());
    }

    @Transactional
    public void deleteTemplate(Long templateId) {
        Template template = queryTemplate(templateId);

        templateRepository.delete(template);
    }

    public TemplateProfileResponse getTemplateProfile(Long templateId) {
        Template template = queryTemplate(templateId);

        return new TemplateProfileResponse(template.getTemplateInfo());
    }

    public List<TemplateProfileResponse> getTemplateList() {
        List<Template> templateList = templateRepository.findAll();

        return templateList.stream()
                .map(t -> new TemplateProfileResponse(t.getTemplateInfo()))
                .collect(Collectors.toList());
    }

    public Template queryTemplate(Long templateId) {
        return templateRepository.findById(templateId).orElseThrow(() -> TemplateNotFoundException.EXCEPTION);
    }
}
