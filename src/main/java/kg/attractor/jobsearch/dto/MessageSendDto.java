package kg.attractor.jobsearch.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageSendDto {
    private Long userFromId;
    private Long userToId;
    private Long respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
}
