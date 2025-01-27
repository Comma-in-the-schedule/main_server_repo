package gdg.comma_in_the_schedule.apiPayload.exception;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.PasswordNotMatchHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(EmailAlreadyExistsHandler.class)
    public ApiResponse<ErrorStatus> handleEmailAlreadyExistsException(EmailAlreadyExistsHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailNotExistsHandler.class)
    public ApiResponse<ErrorStatus> handleEmailNotExistsException(EmailNotExistsHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PasswordNotMatchHandler.class)
    public ApiResponse<ErrorStatus> handlePasswordNotMatchException(PasswordNotMatchHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._INTERNAL_SERVER_ERROR);
    }
}