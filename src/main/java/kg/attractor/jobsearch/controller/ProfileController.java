package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);
        model.addAttribute("user", currentUser);
        model.addAttribute("resumes", resumeService.getAllResumes());
        model.addAttribute("vacancies", vacancyService.getVacancyByCreatorId(String.valueOf(currentUser.getId())));
        return "profile/profile";
    }

    @GetMapping("edit")
    public String editProfile(Model model, String accountType) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);
        model.addAttribute("user", currentUser);
        model.addAttribute("accountType", accountType != null ? accountType : " ");
        return "profile/editProfile";
    }

    @PostMapping("edit")
    public String editProfile(UserDto userDto) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentUser = userService.getUserId(currentUserEmail);
        userService.editUser(UserDto.builder()
                        .name(userDto.getName())
                        .surname(userDto.getSurname())
                        .age(userDto.getAge())
                .build(), currentUser);
        return "redirect:/profile";
    }
}
