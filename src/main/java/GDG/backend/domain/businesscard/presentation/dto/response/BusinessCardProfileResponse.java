package GDG.backend.domain.businesscard.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.WorkType;
import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;

public record BusinessCardProfileResponse(
        Long cardId,

        String name,
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String email,
        String introduction,
        String mbti,
        @Schema(description = "템플릿 URL")
        String template,
        String companyName,
        String position,
        Boolean isMine,
        @Nullable
        List<LinkInfoVO> linkInfoVOList
) {
    public BusinessCardProfileResponse (BusinessCardInfoVO businessCardInfoVO, Boolean isMine,  List<LinkInfoVO> links) {
        this(businessCardInfoVO.cardId(), businessCardInfoVO.name(), businessCardInfoVO.profileImage(), businessCardInfoVO.email()
                , businessCardInfoVO.introduction(), businessCardInfoVO.mbti(), businessCardInfoVO.template(), businessCardInfoVO.companyName()
                , businessCardInfoVO.position(), isMine, links);
    }
}
