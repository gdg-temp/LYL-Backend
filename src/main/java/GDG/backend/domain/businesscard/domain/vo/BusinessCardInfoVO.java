package GDG.backend.domain.businesscard.domain.vo;

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
