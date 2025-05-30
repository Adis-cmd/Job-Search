package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;

    @GetMapping
    public String getAllResume(Model model,
                               @PageableDefault(page = 0, size = 3, sort = "id",
                                       direction = Sort.Direction.ASC)
                               Pageable pageable) {
        Page<ResumeDto> allResume = resumeService.getAllResumeIsActive(pageable);
        model.addAttribute("page", allResume);
        model.addAttribute("currentSort", pageable.getSort().toString());
        model.addAttribute("category", categoryService.findCategoryByResume(allResume));
        model.addAttribute("url", "/resume");
        return "resume/allResume";
    }

    @GetMapping("add")
    public String getAddResume(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user = userService.getUserEmail(currentUserEmail);

        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("user" , user.getAge());
        return "resume/addResume";
    }

    @PostMapping("add")
    public String addResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentUser = String.valueOf(userService.getUserId(currentUserEmail));
        UserDto user = userService.getUserEmail(currentUserEmail);

        if (!bindingResult.hasErrors()) {
            try {
                resumeService.createResumes(resumeDto, currentUser);
                return "redirect:/profile";
            } catch (IllegalArgumentException e) {
                model.addAttribute("errorMessage", e.getMessage());
            }
        }

        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("user" , user.getAge());
        return "resume/addResume";

    }

    @GetMapping("edit/{resumeId}")
    public String getEditResume(@PathVariable("resumeId") String resumeId, Model model, Principal p) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", resumeService.getResumeById(resumeId, p));
        return "resume/editResume";
    }

    @PostMapping("edit/{resumeId}")
    public String getEditResume(@PathVariable("resumeId") String resumeId,
                                @Valid ResumeDto resumeDto,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryService.getAllCategory());
            model.addAttribute("resumeDto", resumeDto);

            return "resume/editResume";

        }
        resumeService.editResume(resumeDto, resumeId);
        return "redirect:/profile";
    }

    @GetMapping("info/{resumeId}")
    public String getResumeInfo(@PathVariable("resumeId") Long resumeId, Model model, @PageableDefault(page = 0, size = 3) Pageable pageable, Principal p) {
        ResumeDto resumeDto = resumeService.getResumeById(String.valueOf(resumeId), p);
        model.addAttribute("category", categoryService.findCategoryById(resumeDto.getCategoryId()));
        model.addAttribute("resumeDto", resumeDto);
        model.addAttribute("applicant", userService.getUserById(resumeDto.getApplicantId()));
        model.addAttribute("workExperiences", workExperienceInfoService.getWorkExperienceInfoByResumeId(resumeDto.getId(), pageable));
        model.addAttribute("educationInfos", educationInfoService.getEducationInfoById(resumeDto.getId(), pageable));
        model.addAttribute("url", "/resume/info/" + resumeDto.getId());
        return "resume/info";
    }


    @PostMapping("/time/{resumeId}")
    public String updateTimeResume(@PathVariable Long resumeId, RedirectAttributes redirectAttributes) {
        try {
            ResumeDto resumeDto = resumeService.updateTime(resumeId);
            redirectAttributes.addFlashAttribute("successMessage", "Время резюме успешно обновлено");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при обновлении времени резюме");
        }
        return "redirect:/profile";
    }

}
