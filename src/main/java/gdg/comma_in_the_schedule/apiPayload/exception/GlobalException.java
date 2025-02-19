package gdg.comma_in_the_schedule.apiPayload.exception;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.*;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt.ExpiredTokenHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt.InvalidTokenHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt.TokenEmptyHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt.UnsupportedTokenHandler;
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
    public ApiResponse<ErrorStatus> handlePasswordNotMatchException(EmailNotVerifiedHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_NOT_VERIFIED);
    }
    @ExceptionHandler(EmailTokenExpiredHandler.class)
    public ApiResponse<ErrorStatus> handleEmailTokenExpiredException(EmailTokenExpiredHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMAIL_TOKEN_EXPIRED);
    }

    @ExceptionHandler(ExpiredTokenHandler.class)
    public ApiResponse<ErrorStatus> handleTokenExpiredException(ExpiredTokenHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._TOKEN_EXPIRED);
    }
    @ExceptionHandler(InvalidTokenHandler.class)
    public ApiResponse<ErrorStatus> handleInvalidTokenException(InvalidTokenHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._INVALID_TOKEN);
    }
    @ExceptionHandler(TokenEmptyHandler.class)
    public ApiResponse<ErrorStatus> handleEmptyTokenException(TokenEmptyHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._EMPTY_TOKEN);
    }
    @ExceptionHandler(UnsupportedTokenHandler.class)
    public ApiResponse<ErrorStatus> handleUnsupportedTokenException(UnsupportedTokenHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._TOKEN_UNSUPPORTED);
    }
    @ExceptionHandler(SurveyNotExistsHandler.class)
    public ApiResponse<ErrorStatus> handleSurveyNotExistsException(SurveyNotExistsHandler handler) {
        return ApiResponse.onFailure(handler.getErrorReason().getCode(), handler.getMessage(), ErrorStatus._SURVEY_NOT_EXISTS);
    }

}