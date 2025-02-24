package gdg.comma_in_the_schedule.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gdg.comma_in_the_schedule.web.dto.activitydto.ActivityResponseDTO;
import gdg.comma_in_the_schedule.web.dto.activitydto.AiResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ResponseDTO {
    private String code;
    private String message;

    @JsonProperty("result")
    private List<AiResponseDTO> result;

}
