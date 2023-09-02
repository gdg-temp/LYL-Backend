package GDG.backend.global.error.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(400, "리프레시 토큰이 유효하지 않습니다"),
    INVALID_ACCESS_TOKEN(400, "Access 토큰이 유효하지 않습니다"),
    User_Not_Host(400, "해당하는 명함의 소유자가 아닙니다."),
    User_Not_STORAGE_Host(400, "해당하는 저장된 명함의 소유자가 아닙니다."),
    EXIST_CARD_STORAGE(400, "해당하는 명함은 이미 저장하였습니다."),
    NOT_REPRESENTATIVE(400, "해당하는 명함은 대표 명함이 아닙니다."),
    IS_REPRESENTATIVE_CARD(400, "해당하는 명함은 대표 명함으로 삭제할 수 없습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_TOKEN(401, "토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),

    /* 403 */
    REGISTER_EXPIRED_TOKEN(403,"만료된 리프레쉬 토큰입니다."),

    /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
    USER_NOT_FOUND(404, "해당하는 정보의 사용자를 찾을 수 없습니다."),
    BUSINESS_CARD_NOT_FOUND(404, "해당하는 정보의 명함을 찾을 수 없습니다."),
    MY_BUSINESS_CARD_LIST_NOT_FOUND(404, "조회되는 명함이 없으므로 명함을 생성해주세요."),
    TEMPLATE_NOT_FOUND(404, "해당하는 정보의 링크를 찾을 수 없습니다."),
    CARD_STORAGE_NOT_FOUND(404, "해당하는 정보의 저장된 명함을 찾을 수 없습니다."),
    LINK_NOT_FOUND(404, "해당하는 정보의 링크를 찾을 수 없습니다."),
    OAUTH_MEMBER_NOT_FOUND(404, "해당하는 로그인 정보를 찾을 수 없습니다."),
    USER_INFO_NOT_FOUND(404, "회원가입을 진행해 주세요."),
    NO_ERROR_TYPE(404, "오류 발생"),

    /* 500 SERVER_ERROR */
    INTERNAL_SERVER_ERROR(500,"서버 에러")
            ;
    private int status;
    private String reason;
}
