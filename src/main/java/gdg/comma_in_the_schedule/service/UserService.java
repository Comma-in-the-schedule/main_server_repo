package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyExistsHandler;
import gdg.comma_in_the_schedule.domain.entity.User;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(UserRequestDTO.RegisterUserRequestDTO registerUserRequestDTO) {
        if (userRepository.existsByEmail(registerUserRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsHandler(ErrorStatus._USER_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(registerUserRequestDTO.getPassword());//비밀번호 암호화

        User user = User.builder()
                        .email(registerUserRequestDTO.getEmail())
                        .password(encodedPassword)
                        .build();
        userRepository.save(user);
    }
}
