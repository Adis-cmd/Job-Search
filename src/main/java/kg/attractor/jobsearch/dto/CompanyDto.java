package kg.attractor.jobsearch.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private Long accountType;
    private Boolean enabled;
}
