package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.modal.Vacancy;

import java.util.List;

public interface VacancyService {
    void editVacancies(VacancyDto vacanciesDto, Long vacancyId);

    void createVacancies(VacancyDto vacanciesDto, Long authorId);

    List<VacancyDto> getVacancies();

    void deleteVacancies(Long vacancyId);

    List<VacancyDto> getAllVacanciesCategory(Long categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<VacancyDto> getAllVacanciesIsActive();
}
