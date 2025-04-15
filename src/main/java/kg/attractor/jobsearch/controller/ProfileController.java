package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        model.addAttribute("resumes", resumeService.getResumeByUserid(String.valueOf(currentUser.getId())));
        model.addAttribute("vacancies", vacancyService.getVacancyByCreatorId(String.valueOf(currentUser.getId())));
        return "profile/profile";
    }

    @GetMapping("edit")
    public String editProfile(Model model, String accountType) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);
        model.addAttribute("userDto", currentUser);
        model.addAttribute("accountType", accountType != null ? accountType : " ");
        return "profile/editProfile";
    }

    @PostMapping("edit")
    public String editProfile(
            @Valid UserDto userDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "profile/editProfile";
        }

        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentUser = userService.getUserId(currentUserEmail);
        String userAvatar = userService.uploadingPhotos(file);

        userService.editUser(UserDto.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .avatar(userAvatar)
                .age(userDto.getAge())
                .build(), currentUser, userAvatar);

        return "redirect:/profile";
    }


}
