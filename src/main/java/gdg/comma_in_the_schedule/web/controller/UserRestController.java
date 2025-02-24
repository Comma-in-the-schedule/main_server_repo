package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.apiPayload.code.status.SuccessStatus;
import gdg.comma_in_the_schedule.service.SurveyService;
import gdg.comma_in_the_schedule.service.UserService;
import gdg.comma_in_the_schedule.web.dto.surveydto.SurveyResponseDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership/auth")
@Slf4j
public class UserRestController {
    private final UserService userService;
    private final SurveyService surveyService;

    /*
    * 회원가입 API
    * */
    @PostMapping("/signup")
    public ApiResponse<?> signUpUser(@RequestBody UserRequestDTO userRequestDTO){
        userService.registerUser(userRequestDTO);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    /*
    * 로그인 API
    * */
    @PostMapping("/login")
    public ApiResponse<?> loginUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO.UserLoginResponseDTO userLoginResponseDTO = userService.loginUser(userRequestDTO);
        return ApiResponse.onSuccess(userLoginResponseDTO);
    }

    /*
    * 설문조사 저장 API
    * */
    @PostMapping("/survey")
    public ApiResponse<?> surveyUser(@RequestHeader("Authorization") String token,
                                     @RequestBody UserRequestDTO.SurveyUserRequestDTO surveyUserRequestDTO){
        surveyService.saveSurvey(surveyUserRequestDTO);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    /*
    * 설문조사 불러오기 API
    * */
    @GetMapping("/survey")
    public ApiResponse<?> getSurvey(@RequestHeader("Authorization") String token,
//                                    @RequestBody UserRequestDTO userRequestDTO
                                    @RequestParam String email){
        SurveyResponseDTO surveyResponseDTO = surveyService.getSurvey(email);
        return ApiResponse.onSuccess(surveyResponseDTO);
    }
}