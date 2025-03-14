package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Resumes {
    private int id;
    private int applicantId;
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
    private Timestamp createdDate;
    private Timestamp updateTime;
}
