package GDG.backend.domain.businesscard.domain.vo;

import GDG.backend.domain.businesscard.domain.WorkType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record BusinessCardInfoVO(
        Long cardId,

        String name,
        String profileImage,
        String email,
        String introduction,
        String mbti,
        String template,
        String companyName,
        String position

) {
}
