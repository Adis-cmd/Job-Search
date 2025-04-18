package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "responded_applicant", schema = "public")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resumeid")
    private Resume resumeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancyid")
    private Vacancy vacancyId;
    @Column(name = "confirmation")
    private Boolean confirmation;
}
