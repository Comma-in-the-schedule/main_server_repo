package gdg.comma_in_the_schedule.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String verificationCode; // UUID 기반 인증 코드

    @Column(nullable = false)
    private Boolean isVerified; //인증 여부 확인

    @Column
    private LocalDateTime expiresAt; // 인증 코드 만료 시간

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
