package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.EditProfileDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model, @PageableDefault(page = 0, size = 3) Pageable pageable) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);
        if (currentUser == null) {
            throw new RuntimeException("User not found");
        }

        Page<ResumeDto> resumePage = resumeService.getResumeByUserid(String.valueOf(currentUser.getId()), pageable);
        Page<VacancyDto> vacancyPage = vacancyService.getVacancyByCreatorId(String.valueOf(currentUser.getId()), pageable);

        if (resumePage.getTotalPages() > 0 && pageable.getPageNumber() >= resumePage.getTotalPages()) {
            return "redirect:/profile?page=" + (resumePage.getTotalPages() - 1) + "&size=" + pageable.getPageSize();
        }

        if (vacancyPage.getTotalPages() > 0 && pageable.getPageNumber() >= vacancyPage.getTotalPages()) {
            return "redirect:/profile?page=" + (vacancyPage.getTotalPages() - 1) + "&size=" + pageable.getPageSize();
        }

        model.addAttribute("page", currentUser);
        model.addAttribute("resumePage", resumePage);
        model.addAttribute("vacancyPage", vacancyPage);
        model.addAttribute("url", "/profile");
        return "profile/profileUser";
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
            @Valid EditProfileDto editProfileDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Long currentUser = userService.getUserId(currentUserEmail);

        UserDto user = userService.getUserById(currentUser);

        String userAvatar = user.getAvatar();
        if (!file.isEmpty()) {
            userAvatar = userService.uploadingPhotos(file);
            editProfileDto.setAvatar(userAvatar);
        }

        if (user.getAccountType() == 1) {
            boolean onlyIgnoredErrors = bindingResult.getFieldErrors().stream()
                    .allMatch(error -> error.getField().equals("surname") || error.getField().equals("age"));

            if (onlyIgnoredErrors) {
                bindingResult = new BeanPropertyBindingResult(editProfileDto, "editProfileDto");
            }
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", editProfileDto);
            return "profile/editProfile";
        }

        userService.editUser(
                UserDto.builder()
                        .name(editProfileDto.getName())
                        .surname(editProfileDto.getSurname())
                        .age(editProfileDto.getAge())
                        .avatar(userAvatar)
                        .build(),
                currentUser,
                userAvatar
        );

        return "redirect:/profile";
    }

//    @GetMapping
//    @ResponseBody
//    public String test() {
//        return "ProfileController работает!";
//    }


}
