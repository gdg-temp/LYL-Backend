package GDG.backend.domain.businesscard.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.template.domain.Template;
import GDG.backend.domain.template.domain.vo.TemplateInfoVO;
import GDG.backend.domain.user.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record BusinessCardProfileResponse(
        @Schema(description = "명함 Id")
        Long id,
        String name,
        String email,
        WorkType workType,
        String job,
        String position,
        String companyName,
        String companyAddress,
        LocalDate birth,
        Gender gender,
        @Schema(description = "템플릿 이미지 URL")
        String templateURL,
        @Schema(description = "명함의 링크 정보")
        List<LinkInfoVO> links,
        @Schema(description = "대표 명함 확인")
        Boolean isRepresentative
) {
    public BusinessCardProfileResponse (BusinessCardInfoVO businessCardInfoVO, List<LinkInfoVO> links) {
        this(businessCardInfoVO.id(), businessCardInfoVO.name(), businessCardInfoVO.email(), businessCardInfoVO.workType(),
                businessCardInfoVO.job(), businessCardInfoVO.position(), businessCardInfoVO.companyName(),
                businessCardInfoVO.companyAddress(), businessCardInfoVO.birth(), businessCardInfoVO.gender(),
                businessCardInfoVO.templateURL(), links, businessCardInfoVO.isRepresentative());
    }
}
