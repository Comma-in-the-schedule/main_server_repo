package gdg.comma_in_the_schedule.apiPayload.exception.handler.jwt;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;
import gdg.comma_in_the_schedule.apiPayload.exception.GlobalException;

public class UnsupportedTokenHandler extends GeneralException {
    public UnsupportedTokenHandler(BaseErrorCode code) {super(code);}
}
