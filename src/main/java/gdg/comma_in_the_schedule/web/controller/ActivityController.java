package gdg.comma_in_the_schedule.web.controller;

import gdg.comma_in_the_schedule.apiPayload.ApiResponse;
import gdg.comma_in_the_schedule.service.ActivityService;
import gdg.comma_in_the_schedule.web.dto.activitydto.ActivityResponseDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/recommendation")
    public ApiResponse<?> recommendAll(@RequestBody UserRequestDTO userRequestDTO){
        List<List<ActivityResponseDTO>> activityResponseDTO = activityService.recommendAll(userRequestDTO);
        return ApiResponse.onSuccess(activityResponseDTO);
    }
}
