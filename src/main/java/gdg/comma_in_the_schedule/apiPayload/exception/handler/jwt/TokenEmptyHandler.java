package gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class TokenEmptyHandler extends GeneralException {
    public TokenEmptyHandler(BaseErrorCode code) {super(code);}
}
