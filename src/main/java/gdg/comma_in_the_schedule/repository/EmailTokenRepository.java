package gdg.comma_in_the_schedule.repository;

import gdg.comma_in_the_schedule.domain.entity.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    boolean existsByEmail(String email);
    Optional<EmailToken> findByEmail(String email);
//    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM EmailToken e WHERE e.email = :email")
//    boolean isEmailExists(@Param("email") String email);
//    @Query("SELECT e FROM EmailToken e WHERE e.email = :email")
//    Optional<EmailToken> findByEmail(@Param("email") String email);
    Optional<EmailToken> findByVerificationCode(String code);

}