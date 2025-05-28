package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface VacancyService {
    List<VacancyDto> searchVacancies(String query);

    VacancyDto getVacancyById(String vacancyId);

    VacancyDto getVacancyByIdEdit(String vacancyId, Principal principal);

    Optional<Vacancy> findVacancyById(Long vacancyId);

    void editVacancies(VacancyDto vacanciesDto, String vacancyId, String email);

    long findCompanyByEmail(String email);

    void createVacancies(VacancyDto vacanciesDto, Authentication authorId);

    Page<VacancyDto> getVacancies(Pageable pageable, String sort, Long categoryId);

    void deleteVacancies(String vacancyId, Authentication email);

    List<VacancyDto> getAllVacanciesCategory(String categoryId);

    List<VacancyDto> getAllVacancyByResponded();


    List<VacancyDto> getAllVacanciesIsActive();

    Page<VacancyDto> getVacancyByCreatorId(String creatorId, Pageable pageable);

    Vacancy converToVacancy(VacancyDto vacancyDto);

    List<VacancyDto> getAllVacancyByCompanyName(Long companyId);
}
