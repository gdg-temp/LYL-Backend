package GDG.backend.domain.businesscard.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.template.domain.Template;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;
import java.util.List;

public record CreateBusinessCardResponse(
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
        Boolean isRepresentative
) {
    public CreateBusinessCardResponse (BusinessCardInfoVO businessCardInfoVO) {
        this(businessCardInfoVO.id(), businessCardInfoVO.name(), businessCardInfoVO.email(), businessCardInfoVO.workType(),
                businessCardInfoVO.job(), businessCardInfoVO.position(), businessCardInfoVO.companyName(),
                businessCardInfoVO.companyAddress(), businessCardInfoVO.birth(), businessCardInfoVO.gender(),
                businessCardInfoVO.isRepresentative());
    }
}
