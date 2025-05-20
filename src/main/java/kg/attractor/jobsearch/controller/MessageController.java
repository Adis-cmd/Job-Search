package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ChatMessageDto;
import kg.attractor.jobsearch.model.AccountType;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;
    private final ResumeService resumeRepository;
    private final VacancyService vacancyRepository;
    private final RespondedApplicantService respondedApplicantRepository;
    private final UserService userRepository;

    @SneakyThrows
    @GetMapping("/chat/{respondedApplicantId}")
    public String getChatMessages(@PathVariable("respondedApplicantId") Long respondedApplicantId, Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
        }

        User user = getUserFromAuth(authentication);

        List<ChatMessageDto> chatMessages = chatService.getAllMessagesByRespondedApplicant(respondedApplicantId);
        RespondedApplicant respondedApplicant = respondedApplicantRepository.findById(respondedApplicantId);
        Long userFromId = user.getId();
        Long userToId;
        if (Objects.equals(userFromId, vacancyRepository.findVacancyById(respondedApplicant.getVacancyId().getId()).orElseThrow().getAuthorId().getId())) {
            userToId = resumeRepository.findResumeById(respondedApplicant.getResumeId().getId()).orElseThrow().getApplicant().getId();
        } else {
            userToId = vacancyRepository.findVacancyById(respondedApplicant.getVacancyId().getId()).orElseThrow().getAuthorId().getId();
        }

        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("respondedApplicantId", respondedApplicant.getId());
        model.addAttribute("userFromId", user.getId());
        model.addAttribute("userToId", userToId);
        model.addAttribute("guestUser", userRepository.findById(userToId));
        return "chat/chat";
    }

    @SneakyThrows
    public User getUserFromAuth(Authentication auth) {
        return userRepository.getUserByEmail(auth.getName());
    }
}
