package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(String vacancyId);

    void editVacancies(VacancyDto vacanciesDto, String vacancyId, String email);

    long findCompanyByEmail(String email);

    void createVacancies(VacancyDto vacanciesDto, Authentication authorId);

    Page<VacancyDto> getVacancies(Pageable pageable, String sort);

    void deleteVacancies(String vacancyId, Authentication email);

    List<VacancyDto> getAllVacanciesCategory(String categoryId);

    List<VacancyDto> getAllVacancyByResponded();

    List<VacancyDto> getAllVacanciesIsActive();

    Page<VacancyDto> getVacancyByCreatorId(String creatorId, Pageable pageable);
}
