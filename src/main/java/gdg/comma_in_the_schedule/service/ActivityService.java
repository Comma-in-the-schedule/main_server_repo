package gdg.comma_in_the_schedule.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.SurveyNotExistsHandler;
import gdg.comma_in_the_schedule.domain.entity.Survey;
import gdg.comma_in_the_schedule.domain.entity.User;
import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import gdg.comma_in_the_schedule.repository.SurveyRepository;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityService {

    private final WebClient webClient;

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    public void recommendAll(UserRequestDTO userRequestDTO){
        //유저 유효성 검사
        User user = userRepository.findByEmail(userRequestDTO.getEmail()).orElseThrow(() -> new EmailNotExistsHandler(ErrorStatus._USER_NOT_EXISTS));

        //설문조사 결과 및 카테고리 추출
        Optional<Survey> survey = surveyRepository.findByUserEmail(user.getEmail());

        String location;
        List<Integer> categories;
        if(survey.isPresent()){
            Survey getSurvey = survey.get();
            location = getSurvey.getLocation();
            categories = getSurvey.getCategory().stream()
                    .map(SurveyCategory::getCode)
                    .collect(Collectors.toList());

            log.info("location: " + location);
            log.info("categories: " + categories.toString());
            //free time은 일단 임시로 지정
        }else{
            throw new SurveyNotExistsHandler(ErrorStatus._SURVEY_NOT_EXISTS);
        }

        //요청 객체 생성
        AIRequestDTO requestBody = AIRequestDTO.builder()
                .location(location)
                .freeTime("2025.02.05.")
                .category(categories)
                .build();

        //AI 서버로 요청
        String aiServerUrl = "http://13.209.119.248:5000/ai/recommendation";
        try{
            Mono<JsonNode> responseMono = webClient.post()
                    .uri(aiServerUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(requestBody), AIRequestDTO.class)
                    .retrieve()
                    .bodyToMono(JsonNode.class);
            responseMono.subscribe(response -> {
                System.out.println("서버 응답: " + response);
            });
        }catch(Exception e){
            throw new RuntimeException("요청 실패");
        }

    }


    @Getter
    public static class AIResponseDTO{

    }

    //ai 서버 요청용 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AIRequestDTO{
        @JsonProperty("location")
        private String location;

        @JsonProperty("free_time")
        private String freeTime;

        @JsonProperty("category")
        private List<Integer> category;
    }
}




