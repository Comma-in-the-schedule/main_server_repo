package gdg.comma_in_the_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //엔티티 생성, 수정 시간 추적
public class CommaInTheScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommaInTheScheduleApplication.class, args);
	}

}
