package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotVerifiedHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.PasswordNotMatchHandler;
import gdg.comma_in_the_schedule.config.jwt.JWToken;
import gdg.comma_in_the_schedule.config.jwt.JwtGenerator;
import gdg.comma_in_the_schedule.domain.entity.EmailToken;
import gdg.comma_in_the_schedule.domain.entity.User;
import gdg.comma_in_the_schedule.repository.EmailTokenRepository;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    private final EmailTokenRepository emailTokenRepository;
    private final EmailService emailService;

    public void registerUser(UserRequestDTO userRequestDTO) {
        Optional<EmailToken> emailToken = emailTokenRepository.findByEmail(userRequestDTO.getEmail());

        if (!emailToken.isPresent()) {
            throw new EmailNotVerifiedHandler(ErrorStatus._EMAIL_NOT_VERIFIED);
        }

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsHandler(ErrorStatus._USER_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());//비밀번호 암호화

        User user = User.builder()
                        .email(userRequestDTO.getEmail())
                        .password(encodedPassword)
                        .build();
        userRepository.save(user);
    }

    public UserResponseDTO.UserLoginResponseDTO loginUser(UserRequestDTO userRequestDTO){
        User user = userRepository.findByEmail(userRequestDTO.getEmail()).orElseThrow(() -> new EmailNotExistsHandler(ErrorStatus._USER_NOT_EXISTS));

        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchHandler(ErrorStatus._PASSSWORD_NOT_MATCH);
        }

        //로그인 성공시 jwt 반환
        JWToken jwToken = jwtGenerator.generateToken(user.getId());
        System.out.println("jwToken: " + jwToken);

        return UserResponseDTO.UserLoginResponseDTO.builder()
                .token(jwToken.getAccessToken())
                .expirationTime(3600L)
                .refreshToken(jwToken.getRefreshToken())
                .userDTO(UserResponseDTO.UserInfoDTO.builder().email(user.getEmail()).build())
                .build();
    }
}
