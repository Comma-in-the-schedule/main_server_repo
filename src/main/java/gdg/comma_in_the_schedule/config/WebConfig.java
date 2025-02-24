package gdg.comma_in_the_schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 모든 경로에 대해 CORS 적용
                .allowedOrigins("*")    // 모든 도메인 허용
                .allowedMethods("*")    // 모든 HTTP 메서드 허용
                .allowedHeaders("*");   // 모든 헤더 허용
        // .allowCredentials(true); // 쿠키나 인증 정보 사용 시 주석 처리 또는 생략해야 함
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}