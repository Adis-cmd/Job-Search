package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String getAllResume(Model model) {
        List<ResumeDto> allResume = resumeService.getAllResumeIsActive();
        model.addAttribute("resumes", allResume);
        return "resume/allResume";
    }

    @GetMapping("add")
    public String getAddResume(Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", new ResumeDto());
        return "resume/addResume";
    }

    @PostMapping("add")
    public String addResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentUser = String.valueOf(userService.getUserId(currentUserEmail));
        resumeDto.setApplicantId(userService.getUserId(currentUserEmail));

        if (!bindingResult.hasErrors()) {
            resumeService.createResumes(resumeDto, currentUser);

            return "redirect:/profile";
        }

        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", resumeDto);
        return "resume/addResume";

    }

    @GetMapping("edit/{resumeId}")
    public String getEditResume(@PathVariable("resumeId") String resumeId, Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("resumeDto", resumeService.getResumeById(resumeId));
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

}
