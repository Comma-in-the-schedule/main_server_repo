package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyVerifiedHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailTokenExpiredHandler;
import gdg.comma_in_the_schedule.config.MailConfig;
import gdg.comma_in_the_schedule.domain.entity.EmailToken;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.repository.EmailTokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailTokenRepository emailTokenRepository;
    private final UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public String generateVerificationCode(){
        return UUID.randomUUID().toString();
    }

    public String sendVerificationEmail(String email) throws RuntimeException {
        try {
            String code = generateVerificationCode(); // 유저 인증을 위한 코드 발급

            boolean isExists = emailTokenRepository.existsByEmail(email);// 이미 인증된 유저인지 확인
            System.out.println(isExists);
            if (!isExists) {

                // 인증 메일 전송
//                String verificationLink = "http://localhost:8080/email/verify?code=" + code;
                String verificationLink = "http://localhost:8080/email/verify"; // 앱에서 해당 링크에 파라미터로 code값을 추가해서 전송

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(sender);
                helper.setTo(email);
                helper.setSubject("쉼표 유저 이메일 인증 요청");
                helper.setText("<h1>이메일 인증</h1><p>아래 링크를 클릭하면 인증이 완료됩니다.</p>" +
                        "<a href=\"" + verificationLink + "\">이메일 인증하기</a>", true);

                mailSender.send(message);

                // 메일을 전송한 후 이메일 토큰 저장
                EmailToken emailToken = EmailToken.builder()
                        .email(email)
                        .verificationCode(code)
                        .expiresAt(LocalDateTime.now().plusMinutes(1))
                        .build(); //

                emailTokenRepository.save(emailToken);
                return code;
            }else{
                throw new EmailAlreadyVerifiedHandler(ErrorStatus._EMAIL_AlREADY_VERIFIED);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 실패: " + e.getMessage()); //
        }
    }


//    @Transactional
    public void verifyEmail(String verificationCode) {
        System.out.println(verificationCode);
        Optional<EmailToken> token= emailTokenRepository.findByVerificationCode(verificationCode);

        System.out.println(token);
        if (token.isPresent()) {
            EmailToken emailToken = token.get();

            if (LocalDateTime.now().isAfter(emailToken.getExpiresAt())) {
                System.out.println("test1");
                emailTokenRepository.delete(emailToken); //만료시 삭제
                System.out.println(emailToken.getVerificationCode());
                System.out.println("test2");

                throw new EmailTokenExpiredHandler(ErrorStatus._EMAIL_TOKEN_EXPIRED);
            }

            emailTokenRepository.save(emailToken);
        }
    }
}