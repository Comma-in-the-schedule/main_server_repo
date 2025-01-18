package gdg.comma_in_the_schedule.apiPayload.code.status;

import gdg.comma_in_the_schedule.apiPayload.code.BaseCode;
import gdg.comma_in_the_schedule.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "OK200", "성공")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}

