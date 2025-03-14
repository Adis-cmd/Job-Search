package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.security.Timestamp;
@Data
public class Vacancies {
    private int id;
    private String description;
    private int categoryId;
    private double salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private int authorId;
    private Timestamp createdDate;
    private Timestamp updatedTime;


}
