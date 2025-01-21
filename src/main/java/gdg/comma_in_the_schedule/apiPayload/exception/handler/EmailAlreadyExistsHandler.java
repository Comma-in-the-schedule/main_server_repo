package gdg.comma_in_the_schedule.apiPayload.exception.handler;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class EmailAlreadyExistsHandler extends GeneralException {
    public EmailAlreadyExistsHandler(BaseErrorCode code) {
        super(code);
    }
}
