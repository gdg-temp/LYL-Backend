package GDG.backend.domain.businesscard.domain.vo;

import java.util.List;

public record BusinessCardInfoVO(
        Long cardId,

        String name,
        String profileImage,
        String email,
        String introduction,
        String styleTemplate,
        String designTemplate,
        List<String> reason,
        String companyName,
        String position,
        String encodeId

) {
}
