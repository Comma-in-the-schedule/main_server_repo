package gdg.comma_in_the_schedule.repository;

import gdg.comma_in_the_schedule.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // userid로 설문 데이터 조회
    List<Survey> findByUserid(Long userid);
}