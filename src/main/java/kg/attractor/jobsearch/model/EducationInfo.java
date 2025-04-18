package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "education_info", schema = "public")
public class EducationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "resumeid")
    private Resume resumeId;
    @Column(name = "institution")
    private String institution;
    @Column(name = "program")
    private String program;
    @Column(name = "startdate")
    private LocalDate startDate;
    @Column(name = "enddate")
    private LocalDate endDate;
    @Column(name = "degree")
    private String degree;
}
