package kg.attractor.jobsearch.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessageDto {
    private Long id;
    private UserDto userFromId;
    private UserDto userToId;
    private Long respondedApplicantId;
    private String content;
    private LocalDateTime timestamp;
}