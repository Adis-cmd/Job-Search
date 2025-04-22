package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyControllerApi {
    private final VacancyService vacanciesService;

    @GetMapping("search/{vacancyId}")
    public VacancyDto getVacancyById(@PathVariable String vacancyId) {
        return vacanciesService.getVacancyById(vacancyId);
    }

//    @PostMapping("add")
//    public HttpStatus createVacancies(@RequestBody @Valid  VacancyDto vacanciesDto, @RequestParam(name = "authorId") String authorId) {
//        vacanciesService.createVacancies(vacanciesDto, authorId);
//        return HttpStatus.CREATED;
//    }

    @PutMapping("update/{vacancyId}")
    public HttpStatus editVacancies(@RequestBody @Valid @PathVariable("vacancyId") VacancyDto vacanciesDto, String vacancyId, Authentication auth) {
        String email = auth.getName();
        vacanciesService.editVacancies(vacanciesDto, vacancyId, email);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{vacanciesId}")
    public HttpStatus deleteVacancies(@PathVariable("vacanciesId") String vacancyId , Authentication auth) {
        vacanciesService.deleteVacancies(vacancyId, auth);
        return HttpStatus.OK;
    }


    @GetMapping("search/category/{categoryId}")
    public List<VacancyDto> allVacanciesCategory(@PathVariable("categoryId") String categoryId) {
        return vacanciesService.getAllVacanciesCategory(categoryId);
    }

    @GetMapping("isActive")
    public List<VacancyDto> allIsActiveVacancies() {
        return vacanciesService.getAllVacanciesIsActive();
    }

    @GetMapping("response")
    public List<VacancyDto> allVacanciesByResponse() {
       return vacanciesService.getAllVacancyByResponded();
    }


}
