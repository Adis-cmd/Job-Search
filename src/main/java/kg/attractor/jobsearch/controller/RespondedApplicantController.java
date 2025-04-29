package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("responded")
public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserService userService;
    private final CategoryService categoryService;


    @GetMapping("{vacancyId}")
    public String respondedApplicants(@PathVariable Long vacancyId, Model model, @PageableDefault(page = 0, size = 3) Pageable pageable) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userId = userService.getUserEmail(currentUserEmail);
        Page<ResumeDto> resumes = resumeService.getResumeByUserid(String.valueOf(userId.getId()), pageable);

        Long categoryId = resumes.stream()
                .map(ResumeDto::getCategoryId)
                .findFirst()
                .orElse(null);

        List<Long> respondedResumeIds = respondedApplicantService.findRespondedResumeIdsByVacancy(vacancyId);

        if (resumes.getTotalPages() > 0 && pageable.getPageNumber() >= resumes.getTotalPages()) {
            return "redirect:/responded/" + vacancyId + "?page=" + (resumes.getTotalPages() - 1) + "&size=" + pageable.getPageSize();
        }

        model.addAttribute("vacancy", vacancyService.getVacancyById(String.valueOf(vacancyId)));
        model.addAttribute("page", resumes);
        model.addAttribute("category", categoryService.findCategoryById(categoryId));
        model.addAttribute("respondedResumeIds", respondedResumeIds);
        model.addAttribute("url", "/responded/" + vacancyId);

        return "responded/main";
    }


    @PostMapping("send")
    public String sendResponded(@RequestParam("vacancyId") Long vacancyId,
                                @RequestParam("resumeId") Long resumeId,
                                RedirectAttributes redirectAttributes) {
        boolean isResponseSuccessful = respondedApplicantService.responseVacancies(vacancyId, resumeId);

        if (!isResponseSuccessful) {
            redirectAttributes.addFlashAttribute("error", "Вы уже откликались на эту вакансию с этим резюме.");
            return "redirect:/info/" + vacancyId;
        }

        return "redirect:/vacancy";
    }


    @GetMapping("info")
    public String getAllRespondedForUser(Model model, Pageable pageable) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);

//        Page<ResumeDto> resumeDto = resumeService.getResumeByUserid(String.valueOf(currentUser.getId()), pageable);
        Page<RespondedApplicantDto> respondedApplicantDto =
                respondedApplicantService.getAllRespondedApplicants(currentUser.getId(), pageable);

        model.addAttribute("page", respondedApplicantDto);
        model.addAttribute("url", "info");

        return "responded/info";
    }
}
