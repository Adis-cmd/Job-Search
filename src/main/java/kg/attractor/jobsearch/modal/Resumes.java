package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Resumes {
    private int id;
    private int applicantId;
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
}
