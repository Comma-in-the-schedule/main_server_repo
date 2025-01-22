package gdg.comma_in_the_schedule.apiPayload.code.status;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    _USER_EXISTS(HttpStatus.BAD_REQUEST, "USER100", "이미 가입된 유저입니다."),
    _USER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "USER101", "해당 유저가 존재하지 않습니다."),
    _PASSSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "USER102", "비밀번호가 일치하지 않습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}