package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class Users {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String accountType;
}
