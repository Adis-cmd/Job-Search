package kg.attractor.jobsearch.service;


import kg.attractor.jobsearch.dto.ChatMessageDto;
import kg.attractor.jobsearch.dto.MessageSendDto;

import java.util.List;

public interface ChatService {
    List<ChatMessageDto> getAllMessagesByRespondedApplicant(Long respondedApplicantId);

    void sendMessage(MessageSendDto messageSendDto, Long respondedApplicantId);
}
