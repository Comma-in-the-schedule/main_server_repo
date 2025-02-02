package gdg.comma_in_the_schedule.apiPayload.exception.handler;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class EmailAlreadyVerifiedHandler extends GeneralException {
    public EmailAlreadyVerifiedHandler(BaseErrorCode code) {
        super(code);
    }

}
