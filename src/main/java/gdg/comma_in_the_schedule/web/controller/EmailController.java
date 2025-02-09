package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.SuccessStatus;
import gdg.comma_in_the_schedule.service.EmailService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    //이메일 인증 링크 발송
    @PostMapping("/send")
    public ApiResponse<?> sendVerificationEmail(@RequestBody EmailDTO emailDTO) throws Exception{
        String code = emailService.sendVerificationEmail(emailDTO.getEmail());
        EmailVerificationDTO emailresponseDTO = EmailVerificationDTO.builder().code(code).build();
        return ApiResponse.onSuccess(emailresponseDTO);
    }

    //인증 링크 클릭 시 이메일 토큰 유효성 체크
    @GetMapping("/verify")
    public ApiResponse<?> verifyEmail(@RequestParam String code) {
        log.info("Verifying email with code {}", code);
        emailService.verifyEmail(code);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    //인증 완료 확인
    @GetMapping("/check")
    public ApiResponse<?> checkEmail(@RequestBody EmailDTO emailDTO){
        log.info("Checking email with code {}", emailDTO.getEmail());
        emailService.checkVerificationComplete(emailDTO.getEmail());
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @Builder
    @Getter
    public static class EmailVerificationDTO {
        private String code;
    }

    @Builder
    @Getter
    public static class EmailDTO {
        private String email;
    }

    @Builder
    @Getter
    public static class CodeDTO {
        private String code;
    }
}