package gdg.comma_in_the_schedule.apiPayload.exception.handler;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class EmailNotExistsHandler extends GeneralException {
    public EmailNotExistsHandler(BaseErrorCode code) {
        super(code);
    }
}
