package kg.attractor.jobsearch.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ContactType {
    private Long id;
    private String type;
}
