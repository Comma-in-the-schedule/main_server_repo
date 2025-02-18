package gdg.comma_in_the_schedule.repository;

import gdg.comma_in_the_schedule.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findByUserEmail(String email);
}
