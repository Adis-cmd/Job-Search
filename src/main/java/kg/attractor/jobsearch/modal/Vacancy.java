package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vacancy {
    private Integer id;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private boolean isActive;
    private Integer authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;

}
