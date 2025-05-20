package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ChatMessageDto;
import kg.attractor.jobsearch.dto.MessageSendDto;
import kg.attractor.jobsearch.model.Message;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repos.MessageRepository;
import kg.attractor.jobsearch.service.ChatService;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl extends MethodClass implements ChatService {
    private final UserService userService;
    private final RespondedApplicantService respondedApplicantService;
    private final MessageRepository messageRepository;

    @Override
    public List<ChatMessageDto> getAllMessagesByRespondedApplicant(Long respondedApplicantId) {
        List<Message> messages = messageRepository.findByRespondedApplicantsId(respondedApplicantId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(MessageSendDto messageSendDto, Long respondedApplicantId) {
        Message message = new Message();
        User userFrom = userService.findById(messageSendDto.getUserFromId());
        User userTo = userService.findById(messageSendDto.getUserToId());
        RespondedApplicant respondedApplicant = respondedApplicantService.findById(respondedApplicantId);

        message.setUserFrom(userFrom);
        message.setUserTo(userTo);
        message.setRespondedApplicants(respondedApplicant);
        message.setContent(messageSendDto.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
    }

    private ChatMessageDto convertToDto(Message message) {
        return ChatMessageDto.builder()
                .id(message.getId())
                .userFromId(userService.getUserById(message.getUserFrom().getId()))
                .userToId(userService.getUserById(message.getUserTo().getId()))
                .respondedApplicantId(message.getRespondedApplicants().getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .build();
    }


}
