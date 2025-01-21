package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailAlreadyExistsHandler;
import gdg.comma_in_the_schedule.domain.entity.User;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserRequestDTO.RegisterUserRequestDTO registerUserRequestDTO) {
        if (userRepository.existsByEmail(registerUserRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsHandler(ErrorStatus._USER_EXISTS);
        }

        User user = User.builder()
                        .email(registerUserRequestDTO.getEmail())
                        .password(registerUserRequestDTO.getPassword())
                        .build();
        userRepository.save(user);
    }
}
