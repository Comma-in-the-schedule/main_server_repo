package gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class InvalidTokenHandler extends GeneralException {
    public InvalidTokenHandler(BaseErrorCode code) {super(code); }
}
