package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "avatar")
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "accounttype_id")
    private AccountType accountType;
    @OneToMany(mappedBy = "applicant")
    private List<Resume> resumes;
    @OneToMany(mappedBy = "authorId")
    private List<Vacancy> vacancies;
    @Column(name = "enabled")
    private Boolean enabled;
}
