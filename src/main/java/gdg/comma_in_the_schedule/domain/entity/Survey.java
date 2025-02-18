package gdg.comma_in_the_schedule.domain.entity;

import gdg.comma_in_the_schedule.domain.common.BaseEntity;
import gdg.comma_in_the_schedule.domain.entity.enums.SurveyCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Survey extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false, unique = true)
    private User user;

    private String nickname;

    private String location;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SurveyCategory> category;
}
