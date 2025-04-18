package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "vacancy", schema = "public")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "expfrom")
    private Integer expFrom;
    @Column(name = "expto")
    private Integer expTo;
    @Column(name = "isactive")
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "authorid")
    private User authorId;
    @Column(name = "createddate")
    private LocalDateTime createdDate;
    @Column(name = "updatedtime")
    private LocalDateTime updatedTime;

}
