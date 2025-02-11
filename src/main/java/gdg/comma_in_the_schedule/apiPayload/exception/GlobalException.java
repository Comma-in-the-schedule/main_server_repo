package gdg.comma_in_the_schedule.apiPayload.exception;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(EmailAlreadyExistsHandler.class)
    public ApiResponse<ErrorStatus> handleEmailAlreadyExistsException(EmailAlreadyExistsHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._USER_EXISTS);
    }

    @ExceptionHandler(EmailNotExistsHandler.class)
    public ApiResponse<ErrorStatus> handleEmailNotExistsException(EmailNotExistsHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._USER_NOT_EXISTS);
    }

    @ExceptionHandler(PasswordNotMatchHandler.class)
    public ApiResponse<ErrorStatus> handlePasswordNotMatchException(PasswordNotMatchHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._PASSSWORD_NOT_MATCH);
    }

    @ExceptionHandler(EmailAlreadyVerifiedHandler.class)
    public ApiResponse<ErrorStatus> handleEmailAlreadyVerifiedException(EmailAlreadyVerifiedHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_AlREADY_VERIFIED);
    }
    @ExceptionHandler(EmailNotVerifiedHandler.class)
    public ApiResponse<ErrorStatus> handleEmailNotVerifiedException(EmailNotVerifiedHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_NOT_VERIFIED);
    }
    @ExceptionHandler(EmailTokenExpiredHandler.class)
    public ApiResponse<ErrorStatus> handleEmailTokenExpiredException(EmailTokenExpiredHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_TOKEN_EXPIRED);
    }

    @ExceptionHandler(UserNotAttemptsVerificationHandler.class)
    public ApiResponse<ErrorStatus> handleUserNotAttempsVerificationException(UserNotAttemptsVerificationHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_TOKEN_NOT_EXISTS);
    }
}