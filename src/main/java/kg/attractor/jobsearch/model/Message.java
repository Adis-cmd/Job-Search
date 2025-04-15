package kg.attractor.jobsearch.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Message {
    private Long id;
    private Integer respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
