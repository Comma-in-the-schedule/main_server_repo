package gdg.comma_in_the_schedule.web.dto.userdto;

import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserRequestDTO {
    private String email;
    private String password;

//    @Getter
//    public static class RegisterUserRequestDTO{
//        private String email;
//        private String password;
//    }


    @Getter
    public static class SurveyUserRequestDTO{
        private String email;
        private String location;
        private String nickname;
        private List<Integer> category;

        public List<SurveyCategory> getCategories() {
            return category.stream()
                    .map(SurveyCategory::fromCode) // 숫자를 ENUM으로 변환
                    .collect(Collectors.toList());
        }
    }


}
