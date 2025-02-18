package gdg.comma_in_the_schedule.apiPayload.exception.handler;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class UserNotAttemptsVerificationHandler extends GeneralException {
    public UserNotAttemptsVerificationHandler(BaseErrorCode code){super(code);}
}
