package gdg.comma_in_the_schedule.web.dto.activitydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AiResponseDTO {
    private SurveyCategory category;
    private String title;
    private String description;
    private String place;
    private String address;
    private String period;

    @JsonProperty("opening_time")
    private String openingTime;

    private String url;
}
