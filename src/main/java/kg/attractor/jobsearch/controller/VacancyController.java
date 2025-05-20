package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String getAllVacancies(Model model,
                                  @PageableDefault(page = 0, size = 3, sort = "id",
                                          direction = Sort.Direction.ASC)
                                  Pageable pageable,
                                  @RequestParam(required = false) String sort) {

        String sortParam = "idAsc";
        if (sort != null && !sort.isEmpty()) {
            String[] sortParts = sort.split(",");
            if (sortParts.length == 2) {
                sortParam = sortParts[0] + (sortParts[1].equalsIgnoreCase("desc") ? "Desc" : "Asc");
            }
        }
        Page<VacancyDto> getAllVacancy = vacancyService.getVacancies(pageable, sortParam);

        if (getAllVacancy.getTotalPages() > 0 && pageable.getPageNumber() >= getAllVacancy.getTotalPages()) {
            return "redirect:/vacancy?page=0&size=" + pageable.getPageSize();
        }

        model.addAttribute("page", getAllVacancy);
        model.addAttribute("currentSort", sort != null ? sort : "");
        model.addAttribute("category", categoryService.findCategoryByVacancy(getAllVacancy));
        model.addAttribute("author", userService.findUserByVacancy(getAllVacancy));
        model.addAttribute("url", "/vacancy");

        return "vacancy/allVacancy";
    }


    @GetMapping("search")
    @ResponseBody
    public List<VacancyDto> findVacanciesByVacancyName(@RequestParam String query) {
        System.out.println("Запрос пришёл: " + query);
        List<VacancyDto> vacancyDtos =  vacancyService.searchVacancies(query);
        System.out.println("Нашли " + vacancyDtos);
        return vacancyDtos;
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
    public String editVacancy(@PathVariable String vacancyId, Model model, Principal p) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("vacancyDto", vacancyService.getVacancyByIdEdit(vacancyId, p));
        return "vacancy/editVacancy";
    }

    @PostMapping("edit/{vacancyId}")
    public String editVacancy(@PathVariable("vacancyId") String vacancyId,
                              @Valid VacancyDto vacancyDto,
                              BindingResult bindingResult,
                              Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        vacancyDto.setAuthorId(vacancyService.findCompanyByEmail(currentUserEmail));
        if (!bindingResult.hasErrors()) {
            vacancyService.editVacancies(vacancyDto, vacancyId, currentUserEmail);
            return "redirect:/profile";
        }
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancy/editVacancy";

    }

    @GetMapping("/info/{id}")
    public String getVacancyById(@PathVariable Long id, Model model) {
        VacancyDto vacancyDto = vacancyService.getVacancyById(String.valueOf(id));
        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("category", categoryService.findCategoryById(vacancyDto.getCategoryId()));
        model.addAttribute("author", userService.getUserById(vacancyDto.getAuthorId()));
        return "vacancy/info";
    }

}
