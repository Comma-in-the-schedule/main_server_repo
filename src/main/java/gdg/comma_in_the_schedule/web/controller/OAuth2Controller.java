package gdg.comma_in_the_schedule.web.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OAuth2Controller {

    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String token) {
//        System.out.println("token: " + token);
        System.out.println("test");
        RestTemplate restTemplate = new RestTemplate();

        // Bearer 제거 후 토큰만 추출
        String accessToken = token.replace("Bearer ", "");
        System.out.println("access Token: " +  accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://www.googleapis.com/oauth2/v2/userinfo";

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return ResponseEntity.ok(response.getBody());
    }
}
