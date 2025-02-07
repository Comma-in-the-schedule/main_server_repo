package gdg.comma_in_the_schedule.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.util.Arrays;

@Configuration
public class EnvConfig {

    @Bean
    public CommandLineRunner printEnvironmentVariables(Environment env) {
        return args -> {
            System.out.println("==== Application Environment Variables ====");
            System.out.println("MYSQL_HOST: " + env.getProperty("MYSQL_HOST"));
            System.out.println("MYSQL_DATABASE: " + env.getProperty("MYSQL_DATABASE"));
            System.out.println("MYSQL_USER: " + env.getProperty("MYSQL_USER"));
            System.out.println("MYSQL_PASSWORD: " + env.getProperty("MYSQL_PASSWORD"));
            System.out.println("MAIL_HOST: " + env.getProperty("spring.mail.host"));
            System.out.println("MAIL_USERNAME: " + env.getProperty("spring.mail.username"));
            System.out.println("MAIL_PASSWORD: " + env.getProperty("spring.mail.password"));
            System.out.println("JWT_SECRET: " + env.getProperty("jwt.secret"));

            // 모든 환경 변수 출력
            System.out.println("\n==== All Environment Properties ====");
            Arrays.stream(env.getActiveProfiles()).forEach(profile ->
                    System.out.println("Active Profile: " + profile)
            );
        };
    }
}
