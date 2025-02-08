package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyVerifiedHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotVerifiedHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailTokenExpiredHandler;
import gdg.comma_in_the_schedule.config.MailConfig;
import gdg.comma_in_the_schedule.domain.entity.EmailToken;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.repository.EmailTokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
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

//    @Transactional
    public String sendVerificationEmail(String email) throws Exception {
        try {
            String code = generateVerificationCode(); // 유저 인증을 위한 코드 발급

            boolean isExists = emailTokenRepository.existsByEmail(email);// 이미 인증된 유저인지 확인

            if (!isExists){
            //이메일 토큰 저장
                EmailToken emailToken = EmailToken.builder()
                        .email(email)
                        .verificationCode(code)
                        .isVerified(false)
                        .expiresAt(LocalDateTime.now().plusMinutes(1))
                        .build(); //
                try {
                    emailTokenRepository.save(emailToken);
                    log.info("이메일 토큰이 저장되었습니다.");
                    log.info(emailTokenRepository.findById(emailToken.getId()).toString());


                }catch (Exception e){
                    log.info("이메일 토큰 저장 실패", e);
                }


                // 인증 메일 전송
//                String verificationLink = "http://13.209.119.248:8080/email/verify?code=" + code;

                MimeMessage message = mailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//                helper.setFrom(sender);
//                helper.setTo(email);
//                helper.setSubject("쉼표 유저 이메일 인증 요청");
//                helper.setText("<h1>이메일 인증</h1><p>아래 링크를 클릭하면 인증이 완료됩니다.</p>" +
//                        "<a href=\"" + verificationLink + "\">이메일 인증하기</a>", true);
//
//                mailSender.send(message);

                message.addRecipients(MimeMessage.RecipientType.TO, email);// 보내는 대상
                message.setSubject("쉼표 유저 이메일 인증 요청");// 제목

                String body = "<div>"
                        + "<h1> 안녕하세요. 쉼표 입니다.</h1>"
                        + "<br>"
                        + "<p>아래 링크를 클릭하면 이메일 인증이 완료됩니다.<p>"
                        + "<a href='http://13.209.119.248:8080/email/verify?code=" + code + "'>인증 링크</a>"
                        + "</div>";

                message.setText(body, "utf-8", "html");// 내용, charset 타입, subtype
                // 보내는 사람의 이메일 주소, 보내는 사람 이름
                message.setFrom(new InternetAddress("shimhun99@gmail.com", "쉼표 관리자"));// 보내는 사람
                mailSender.send(message); // 메일 전송

                return code;
            }else{
                throw new EmailAlreadyVerifiedHandler(ErrorStatus._EMAIL_AlREADY_VERIFIED);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 실패: " + e.getMessage()); //
        }
    }


    public void verifyEmail(String verificationCode) {
        Optional<EmailToken> token= emailTokenRepository.findByVerificationCode(verificationCode);

        if (token.isPresent()) {
            log.info("이메일 토큰이 존재합니다. 즉, 해당 코드는 서버에서 발급한 유효한 코드입니다");
            EmailToken emailToken = token.get();

            if (LocalDateTime.now().isAfter(emailToken.getExpiresAt())) {
                emailTokenRepository.delete(emailToken); //만료시 삭제
                throw new EmailTokenExpiredHandler(ErrorStatus._EMAIL_TOKEN_EXPIRED);
            } else{
                log.info("아직 이메일 토큰이 유효합니다.");
                emailToken.setVerified(true);
                emailTokenRepository.save(emailToken);
            }
        }
    }

    public void checkVerificationComplete(String email){
        Optional<EmailToken> token= emailTokenRepository.findByEmail(email);
        if(token.isPresent()){
            EmailToken emailToken = token.get();
            if(!emailToken.getIsVerified()){ //인증이 완료되지 않았을 때
                throw new EmailNotVerifiedHandler(ErrorStatus._EMAIL_NOT_VERIFIED);
            }
        }
    }
}