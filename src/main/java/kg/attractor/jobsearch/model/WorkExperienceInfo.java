package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "work_experience_info", schema = "public")
public class WorkExperienceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "resumeid")
    private Resume resumeId;
    @Column(name = "years")
    private Integer years;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "position")
    private String position;
    @Column(name = "responsibilities")
    private String responsibilities;
}
