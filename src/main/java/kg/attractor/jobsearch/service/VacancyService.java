package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(String vacancyId);

    void editVacancies(VacancyDto vacanciesDto, String vacancyId, String email);

    long findCompanyByEmail(String email);

    void createVacancies(VacancyDto vacanciesDto, String authorId);

    List<VacancyDto> getVacancies();

    void deleteVacancies(String vacancyId, String email);

    List<VacancyDto> getAllVacanciesCategory(String categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<VacancyDto> getAllVacanciesIsActive();

    List<VacancyDto> getVacancyByCreatorId(String creatorId);
}
