package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.dto.ChatMessageDto;
import kg.attractor.jobsearch.dto.MessageSendDto;
import kg.attractor.jobsearch.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MessageControllerApi {
    private final ChatService chatService;

    @GetMapping("/{respondedApplicantId}/messages")
    public List<ChatMessageDto> getChatMessages(@PathVariable("respondedApplicantId") Long respondedApplicantId) {
        return chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
    }

    @PostMapping("/{respondedApplicantId}/send")
    public ResponseEntity<?> sendMessage(@PathVariable Long respondedApplicantId, @RequestBody MessageSendDto messageSendDto) {
        try {
            chatService.sendMessage(messageSendDto, respondedApplicantId);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending message: " + e.getMessage());
        }
    }
}
