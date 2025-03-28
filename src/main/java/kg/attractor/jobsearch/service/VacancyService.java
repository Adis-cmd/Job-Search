package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.modal.Vacancy;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(String vacancyId);

    void editVacancies(VacancyDto vacanciesDto, String vacancyId);

    void createVacancies(VacancyDto vacanciesDto, String authorId);

    List<VacancyDto> getVacancies();

    void deleteVacancies(String vacancyId);

    List<VacancyDto> getAllVacanciesCategory(String categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<VacancyDto> getAllVacanciesIsActive();
}
