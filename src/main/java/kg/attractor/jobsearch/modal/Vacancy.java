package kg.attractor.jobsearch.modal;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;

}
