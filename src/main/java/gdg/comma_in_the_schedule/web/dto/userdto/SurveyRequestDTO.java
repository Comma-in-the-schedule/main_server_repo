package gdg.comma_in_the_schedule.web.dto.userdto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SurveyRequestDTO {
    private Long userId;
    private String location;
    private String hobby;
    private String nickname;
}