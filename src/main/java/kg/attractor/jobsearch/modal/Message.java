package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Integer id;
    private Integer respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
