package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Messages {
    private int id;
    private int respondedApplicants;
    private String content;
    private Timestamp timestamp;
}
