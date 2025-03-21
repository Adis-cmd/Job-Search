package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacanciesService;

    @PostMapping("add")
    public HttpStatus createVacancies(@RequestBody VacancyDto vacanciesDto) {
        vacanciesService.createVacancies(vacanciesDto);
        return HttpStatus.CREATED;
    }

    @PutMapping("update/{vacancyId}")
    public HttpStatus editVacancies(@PathVariable("vacancyId") VacancyDto vacanciesDto, int vacancyId) {
        vacanciesService.editVacancies(vacanciesDto, vacancyId);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{vacanciesId}")
    public HttpStatus deleteVacancies(@PathVariable("vacanciesId") Integer vacancyId, Vacancy vacancies) {
        vacanciesService.deleteVacancies(vacancyId, vacancies);
        return HttpStatus.OK;
    }

    @GetMapping
    public List<VacancyDto> getVacancies() {
        return vacanciesService.getVacancies();
    }

    @GetMapping("search/category/{categoryId}")
    public List<VacancyDto> allVacanciesCategory(@PathVariable("categoryId") Long categoryId) {
        return vacanciesService.getAllVacanciesCategory(categoryId);
    }

    @GetMapping("isActive")
    public HttpStatus allIsActiveVacancies(Vacancy vacancies) {
        vacanciesService.getAllVacanciesIsActive(vacancies);
        return HttpStatus.OK;
    }

    @GetMapping("response")
    public List<VacancyDto> allVacanciesByResponse() {
       return vacanciesService.getAllVacancyByResponded();
    }


}
