package gdg.comma_in_the_schedule.config.jwt;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class JWToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
