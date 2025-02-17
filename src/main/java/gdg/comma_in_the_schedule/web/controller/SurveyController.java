package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.service.SurveyService;
import gdg.comma_in_the_schedule.web.dto.userdto.SurveyRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership/survey")
public class SurveyController {

    private final SurveyService surveyService;

    // POST: 설문 데이터 저장
    @PostMapping
    public ApiResponse<?> createSurvey(@RequestBody SurveyRequestDTO request) {
        surveyService.createSurvey(request);
        return ApiResponse.onSuccess(null);
    }

    // GET: 특정 userid로 설문 데이터 조회
    @GetMapping("/user/{userid}")
    public ApiResponse<List<?>> getSurveysByUserid(@PathVariable Long userid) {
        List<?> surveys = surveyService.getSurveysByUserid(userid);

        // 데이터가 없는 경우 실패 응답 반환
        if (surveys.isEmpty()) {
            return ApiResponse.onFailure("404", "User ID " + userid + " does not exist", null);
        }

        // 데이터가 있는 경우 성공 응답 반환
        return ApiResponse.onSuccess(surveys);
    }
}
