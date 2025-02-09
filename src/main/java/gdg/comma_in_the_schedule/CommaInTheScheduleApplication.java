package gdg.comma_in_the_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing //엔티티 생성, 수정 시간 추적
public class CommaInTheScheduleApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommaInTheScheduleApplication.class, args);

//		ConfigurableApplicationContext context = SpringApplication.run(CommaInTheScheduleApplication.class, args);
//
//		ConfigurableEnvironment env = context.getEnvironment();
//
//		System.out.println("==== Application Environment Variables ====");
//		System.out.println("MYSQL_HOST: " + env.getProperty("MYSQL_HOST"));
//		System.out.println("MYSQL_DATABASE: " + env.getProperty("MYSQL_DATABASE"));
//		System.out.println("MYSQL_USER: " + env.getProperty("MYSQL_USER"));
//		System.out.println("MYSQL_PASSWORD: " + env.getProperty("MYSQL_PASSWORD"));
//		System.out.println("MAIL_HOST: " + env.getProperty("spring.mail.host"));
//		System.out.println("MAIL_USERNAME: " + env.getProperty("spring.mail.username"));
//		System.out.println("MAIL_PASSWORD: " + env.getProperty("spring.mail.password"));
//		System.out.println("JWT_SECRET: " + env.getProperty("jwt.secret"));
	}

}
