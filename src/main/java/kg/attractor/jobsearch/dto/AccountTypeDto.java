package kg.attractor.jobsearch.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AccountTypeDto {
    private Long id;
    private String type;

}
