package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.SuccessStatus;
import gdg.comma_in_the_schedule.service.UserService;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/login")
//    public ApiResponse<SuccessStatus> loginUser(@RequestBody UserRequestDTO.LoginUserRequestDTO loginUserRequestDTO){
//        userService.loginUser(userLoginRequestDTO);
//        return ApiResponse.onSuccess(SuccessStatus._OK);
//    }
}