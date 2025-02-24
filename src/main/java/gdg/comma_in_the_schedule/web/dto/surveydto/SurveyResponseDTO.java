package gdg.comma_in_the_schedule.web.dto.surveydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDTO{
    private String nickName;

    private String location;

    private List<Integer> category;
}