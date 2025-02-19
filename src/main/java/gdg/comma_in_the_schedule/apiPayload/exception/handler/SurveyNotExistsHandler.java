package gdg.comma_in_the_schedule.apiPayload.exception.handler;

import gdg.comma_in_the_schedule.apiPayload.code.BaseErrorCode;
import gdg.comma_in_the_schedule.apiPayload.exception.GeneralException;

public class SurveyNotExistsHandler extends GeneralException {
    public SurveyNotExistsHandler(BaseErrorCode code) {super(code);}
}
