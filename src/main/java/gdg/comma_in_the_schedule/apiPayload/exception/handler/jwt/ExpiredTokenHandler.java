package gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class ExpiredTokenHandler extends GeneralException {
    public ExpiredTokenHandler(BaseErrorCode code) {super(code); }
}