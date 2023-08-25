package GDG.backend.domain.businesscard.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.user.domain.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record BusinessCardProfileResponse(
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
        List<LinkInfoVO> links,
        Boolean isRepresentative
) {
    public BusinessCardProfileResponse (BusinessCardInfoVO businessCardInfoVO) {
        this(businessCardInfoVO.id(), businessCardInfoVO.name(), businessCardInfoVO.email(), businessCardInfoVO.workType(),
                businessCardInfoVO.job(), businessCardInfoVO.position(), businessCardInfoVO.companyName(),
                businessCardInfoVO.companyAddress(), businessCardInfoVO.birth(), businessCardInfoVO.gender(),
                businessCardInfoVO.links().stream().map(l -> new LinkInfoVO(l.getId(), l.getLinkType(), l.getLinkUrl(), l.getLinkText())).collect(Collectors.toList()),
                businessCardInfoVO.isRepresentative());
    }
}
