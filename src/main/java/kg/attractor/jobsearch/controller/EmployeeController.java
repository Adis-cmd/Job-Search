package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("company")
public class EmployeeController {
    private final UserService userService;
    private final VacancyService vacancyService;

    @GetMapping
    public String allCompany(Model model,
                             @PageableDefault(page = 0, size = 5, sort = "id",
                                     direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserDto> user = userService.findAllUserEmployee(pageable);

        if (user.getTotalPages() > 0 && pageable.getPageNumber() >= user.getTotalPages()) {
            return "redirect:/company?page=0&size=" + pageable.getPageSize();
        }

        model.addAttribute("page", user);
        model.addAttribute("url", "/company");

        return "company/allCompany";
    }

    @GetMapping("info/{id}")
    public String infoCompany(@PathVariable("id") Long id, Model model, @PageableDefault(page = 0, size = 2, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        UserDto user = userService.findUserEmployeeById(id);
        Page<VacancyDto> vacancyDtos = vacancyService.getVacancyByCreatorId(String.valueOf(id), pageable);

        if (vacancyDtos.getTotalPages() > 0 && pageable.getPageNumber() >= vacancyDtos.getTotalPages()) {
            return "redirect:/company/info?page=0&size=" + pageable.getPageSize();
        }
        model.addAttribute("user", user);
        model.addAttribute("vacancyDtos", vacancyDtos);
        return "company/infoCompany";
    }

}
