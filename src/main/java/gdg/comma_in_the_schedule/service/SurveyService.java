package gdg.comma_in_the_schedule.service;

import gdg.comma_in_the_schedule.domain.entity.Survey;
import gdg.comma_in_the_schedule.repository.SurveyRepository;
import gdg.comma_in_the_schedule.web.dto.userdto.SurveyRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyRepository surveyRepository;

    // POST: 설문 데이터 저장
    @Transactional
    public void createSurvey(SurveyRequestDTO request) {
        Survey survey = Survey.builder()
                .userid(request.getUserId())
                .location(request.getLocation())
                .hobby(request.getHobby())
                .nickname(request.getNickname())
                .build();

        surveyRepository.save(survey);
    }

    // GET: 특정 userid로 설문 데이터 조회
    public List<Survey> getSurveysByUserid(Long userid) {
        return surveyRepository.findByUserid(userid);
    }
}
