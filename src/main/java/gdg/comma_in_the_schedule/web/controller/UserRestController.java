package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.SuccessStatus;
import gdg.comma_in_the_schedule.service.UserService;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership/auth")
@Slf4j
public class UserRestController {
    private final UserService userService;

//    UserRestController(UserService userService) {
//        this.userService = userService;
//    }


    /*
    * 회원가입 API
    * */
    @PostMapping("/signup")
    public ApiResponse<SuccessStatus> signUpUser(@RequestBody UserRequestDTO userRequestDTO){
        userService.registerUser(userRequestDTO);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponseDTO.UserLoginResponseDTO> loginUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO.UserLoginResponseDTO userLoginResponseDTO = userService.loginUser(userRequestDTO);
        return ApiResponse.onSuccess(userLoginResponseDTO);
    }

    @GetMapping("/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        // 현재 로그인한 사용자 정보 반환
        System.out.println(principal.getAttributes());
        return principal.getAttributes();
    }
}