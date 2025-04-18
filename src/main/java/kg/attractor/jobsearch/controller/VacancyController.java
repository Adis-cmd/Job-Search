package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String getAllVacancies(Model model) {
        List<VacancyDto> getAllVacancy = vacancyService.getVacancies();

        model.addAttribute("vacancies", getAllVacancy);
        return "vacancy/allVacancy";
    }


    @GetMapping("add")
    public String addVacancy(Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("vacancyDto", new VacancyDto());
        return "vacancy/addVacancy";
    }

    @PostMapping("add")
    public String addVacancy(@Valid @ModelAttribute("vacancyDto") VacancyDto vacancyDto,
                             BindingResult bindingResult,
                             Authentication authentication,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryService.getAllCategory());
            return "vacancy/addVacancy";
        }

        vacancyService.createVacancies(vacancyDto, authentication);

        return "redirect:/profile";
    }

    @GetMapping("edit/{vacancyId}")
    public String editVacancy(@PathVariable String vacancyId, Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("vacancyDto", vacancyService.getVacancyById(vacancyId));
        return "vacancy/editVacancy";
    }

    @PostMapping("edit/{vacancyId}")
    public String editVacancy(@PathVariable("vacancyId") String vacancyId,
                              @Valid VacancyDto vacancyDto,
                              BindingResult bindingResult,
                              Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        vacancyDto.setAuthorId(vacancyService.findCompanyByEmail(currentUserEmail));

        if (!bindingResult.hasErrors()) {
            vacancyService.editVacancies(vacancyDto, vacancyId, currentUserEmail);
            return "redirect:/profile";
        }
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancy/editVacancy";

    }

}
