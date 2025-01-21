package gdg.comma_in_the_schedule.web.dto.userdto;

import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class RegisterUserRequestDTO{
        private String email;
        private String password;
    }
}
