package gdg.comma_in_the_schedule.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import gdg.comma_in_the_schedule.apiPayload.code.status.ErrorStatus;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.EmailNotExistsHandler;
import gdg.comma_in_the_schedule.apiPayload.exception.handler.SurveyNotExistsHandler;
import gdg.comma_in_the_schedule.domain.entity.Survey;
import gdg.comma_in_the_schedule.domain.entity.User;
import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import gdg.comma_in_the_schedule.repository.SurveyRepository;
import gdg.comma_in_the_schedule.repository.UserRepository;
import gdg.comma_in_the_schedule.web.dto.surveydto.SurveyResponseDTO;
import gdg.comma_in_the_schedule.web.dto.userdto.UserRequestDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    public void saveSurvey(UserRequestDTO.SurveyUserRequestDTO surveyUserRequestDTO) {
        //이메일 유효성 검증
        User user = userRepository.findByEmail(surveyUserRequestDTO.getEmail()).orElseThrow(() -> new EmailNotExistsHandler(ErrorStatus._USER_NOT_EXISTS));

        Optional<Survey> byUserEmail = surveyRepository.findByUserEmail(user.getEmail());

        if(byUserEmail.isPresent()){
            Survey survey = byUserEmail.get();
            survey.setNickname(surveyUserRequestDTO.getNickname());
            survey.setLocation(surveyUserRequestDTO.getLocation());
            survey.setCategory(surveyUserRequestDTO.getCategories());
            surveyRepository.save(survey);
        }else{
            Survey survey = Survey.builder()
                    .user(user)
                    .nickname(surveyUserRequestDTO.getNickname())
                    .location(surveyUserRequestDTO.getLocation())
                    .category(surveyUserRequestDTO.getCategories())
                    .build();

            surveyRepository.save(survey);
        }
    }

    public SurveyResponseDTO getSurvey(String email){
        //이메일 유효성 검증
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistsHandler(ErrorStatus._USER_NOT_EXISTS));

        Optional<Survey> survey = surveyRepository.findByUserEmail(user.getEmail());

        //유저가 설문조사 진행했는지 점검
        if(survey.isPresent()){
            Survey getSurvey = survey.get();
            String nickname = getSurvey.getNickname();
            String location = getSurvey.getLocation();

            List<Integer> categoriesToInt = getSurvey.getCategory().stream()
                    .map(SurveyCategory::toCode)
                    .collect(Collectors.toList());

            return SurveyResponseDTO.builder()
                    .nickName(nickname)
                    .location(location)
                    .category(categoriesToInt)
                    .build();
        }else{
            throw new SurveyNotExistsHandler(ErrorStatus._SURVEY_NOT_EXISTS);
        }

    }
}
