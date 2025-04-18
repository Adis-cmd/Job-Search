package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(String vacancyId);

    void editVacancies(VacancyDto vacanciesDto, String vacancyId, String email);

    long findCompanyByEmail(String email);

    void createVacancies(VacancyDto vacanciesDto, Authentication authorId);

    List<VacancyDto> getVacancies();

    void deleteVacancies(String vacancyId, Authentication email);

    List<VacancyDto> getAllVacanciesCategory(String categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<VacancyDto> getAllVacanciesIsActive();

    List<VacancyDto> getVacancyByCreatorId(String creatorId);
}
