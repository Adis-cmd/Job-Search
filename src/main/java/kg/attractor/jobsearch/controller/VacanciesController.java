package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.VacanciesDto;
import kg.attractor.jobsearch.modal.Vacancies;
import kg.attractor.jobsearch.service.VacanciesService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController {
    private final VacanciesService vacanciesService;

    @PostMapping("add")
    public HttpStatus createVacancies(@RequestBody VacanciesDto vacanciesDto) {
        vacanciesService.createVacancies(vacanciesDto);
        return HttpStatus.CREATED;
    }

    @PutMapping("update/{vacancyId}")
    public HttpStatus editVacancies(@PathVariable("vacancyId") VacanciesDto vacanciesDto, int vacancyId) {
        vacanciesService.editVacancies(vacanciesDto, vacancyId);
        return HttpStatus.OK;
    }

    @GetMapping("search")
    public HttpStatus searchVacancies(Vacancies vacancies, User user) {
        vacanciesService.searchSuccessfulApplicants(vacancies, user);
        return HttpStatus.OK;
    }

    @PostMapping("response/{vacanciesId}")
    public HttpStatus responseVacancies(@PathVariable("vacanciesId") Integer vacanciesId, User user) {
        vacanciesService.responseVacancies(vacanciesId, user);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{vacanciesId}")
    public HttpStatus deleteVacancies(@PathVariable("vacanciesId") Integer vacancyId, Vacancies vacancies) {
        vacanciesService.deleteVacancies(vacancyId, vacancies);
        return HttpStatus.OK;
    }

    @GetMapping("search/category/{categoryId}")
    public HttpStatus allVacanciesCategory(@PathVariable("categoryId") Integer categoryId, Vacancies vacancies) {
        vacanciesService.getAllVacanciesCategory(categoryId, vacancies);
        return HttpStatus.OK;
    }

    @GetMapping("isActive")
    public HttpStatus allIsActiveVacancies(Vacancies vacancies) {
        vacanciesService.getAllVacanciesIsActive(vacancies);
        return HttpStatus.OK;
    }
}
