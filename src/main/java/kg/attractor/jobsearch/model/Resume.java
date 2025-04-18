package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "resume", schema = "public")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "applicantid")
    private User applicant;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "isactive")
    private Boolean isActive;
    @Column(name = "createddate")
    private LocalDateTime createdDate;
    @Column(name = "updatetime")
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "resumeId", fetch = FetchType.LAZY)
    private List<WorkExperienceInfo> workExperiences;
    @OneToMany(mappedBy = "resumeId", fetch = FetchType.LAZY)
    private List<EducationInfo> educationInfos;
}
