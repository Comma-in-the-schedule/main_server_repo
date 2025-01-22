package gdg.comma_in_the_schedule.web.dto.userdto;

import gdg.comma_in_the_schedule.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
//    private String email;

    @Getter
    @Builder
    public static class UserLoginResponseDTO{
        private Long expirationTime;
        private UserInfoDTO userDTO;
        private String token;
        private String refreshToken;
    }

    @Getter
    @Builder
    public static class UserInfoDTO{
        private String email;
    }
}
