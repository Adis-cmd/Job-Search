package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Messages {
    private int id;
    private int respondedApplicants;
    private String content;
    private LocalDate timestamp;
}
