package gdg.comma_in_the_schedule.web.dto.activitydto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {
//    public static class fromAi{
//
//    }


//    @JsonProperty("category_code")
    private int category;

    private String title;
    private String description;
    private String place;
    private String address;
    private String period;

    @JsonProperty("opening_time")
    private String openingTime;

    private String url;
    private String image;

    @Getter
    @AllArgsConstructor
    public static class ActivityListResponseDTO{
        private List<ActivityResponseDTO> activities;
    }

    public static ActivityResponseDTO fromJsonNode(JsonNode node) {
                return ActivityResponseDTO.builder()
                        .category(SurveyCategory.fromString(node.get("category").asText()))
                        .title(node.get("title").asText())
                        .description(node.get("description").asText())
                        .place(node.get("place").asText())
                        .address(node.get("address").asText())
                        .period(node.get("period").asText())
                        .openingTime(node.get("opening_time").asText())
                        .url(node.get("url").asText())
                        .build();
    }
}
