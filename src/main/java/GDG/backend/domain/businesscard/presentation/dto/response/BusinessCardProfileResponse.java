package GDG.backend.domain.businesscard.presentation.dto.response;

import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.link.domain.vo.LinkInfoVO;
import GDG.backend.domain.reason.domain.vo.ReasonInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.util.List;

public record BusinessCardProfileResponse(
        String encodeId,
        Long cardId,
        String name,
        @Schema(description = "프로필 이미지 URL")
        String profileImage,
        String email,
        String introduction,
        String styleTemplate,
        String designTemplate,
        @Nullable
        String companyName,
        @Nullable
        String position,
        Boolean isMine,
        List<ReasonInfoVO> reasonInfoVOList,
        @Nullable
        List<LinkInfoVO> linkInfoVOList
) {
    public BusinessCardProfileResponse (String encodeId, BusinessCardInfoVO businessCardInfoVO, Boolean isMine, List<ReasonInfoVO> reasonInfoVOList,  List<LinkInfoVO> links) {
        this(encodeId, businessCardInfoVO.cardId(), businessCardInfoVO.name(), businessCardInfoVO.profileImage(), businessCardInfoVO.email()
                , businessCardInfoVO.introduction(), businessCardInfoVO.styleTemplate(), businessCardInfoVO.designTemplate()
                , businessCardInfoVO.companyName(), businessCardInfoVO.position(), isMine, reasonInfoVOList, links);
    }
}
