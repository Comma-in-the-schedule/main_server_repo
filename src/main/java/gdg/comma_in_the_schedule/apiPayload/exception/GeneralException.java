package gdg.comma_in_the_schedule.apiPayload.exception;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.code.ErrorReasonDTO;

public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        super(code.getReason().getMessage());
        this.code = code;
    }

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
