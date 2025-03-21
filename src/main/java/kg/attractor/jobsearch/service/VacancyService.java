package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.modal.Vacancy;

import java.util.List;

public interface VacancyService {
    void editVacancies(VacancyDto vacanciesDto, int vacancyId);

    void createVacancies(VacancyDto vacanciesDto);

    List<VacancyDto> getVacancies();

    void deleteVacancies(Integer vacancyId, Vacancy vacancies);

    List<VacancyDto> getAllVacanciesCategory(Long categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<Vacancy> getAllVacanciesIsActive(Vacancy vacancies);
}
