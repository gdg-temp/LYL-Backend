package GDG.backend.domain.template.service;

import GDG.backend.domain.businesscard.domain.BusinessCard;
import GDG.backend.domain.businesscard.service.BusinessCardServiceUtils;
import GDG.backend.domain.template.domain.Template;
import GDG.backend.domain.template.domain.respository.TemplateRepository;
import GDG.backend.domain.template.exception.TemplateNotFoundException;
import GDG.backend.domain.template.presentation.dto.request.AddTemplateRequest;
import GDG.backend.domain.template.presentation.dto.response.TemplateProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final BusinessCardServiceUtils businessCardServiceUtils;

    @Transactional
    public TemplateProfileResponse addTemplate(Long cardId, AddTemplateRequest addTemplateRequest) {
        BusinessCard businessCard = businessCardServiceUtils.queryBusinessCard(cardId);

        Template template = Template.addTemplate(businessCard, addTemplateRequest.templateUrl());

        templateRepository.save(template);

        return new TemplateProfileResponse(template.getTemplateInfo());
    }

    public Template queryTemplate(Long templateId) {
        return templateRepository.findById(templateId).orElseThrow(() -> TemplateNotFoundException.EXCEPTION);
    }
}
