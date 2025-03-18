package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacanciesDto;
import kg.attractor.jobsearch.modal.Vacancy;
import org.apache.catalina.User;

import java.util.List;

public interface VacanciesService {
    void editVacancies(VacanciesDto vacanciesDto, int vacancyId);

    void createVacancies(VacanciesDto vacanciesDto);

    void responseVacancies(Integer vacanciesId, User user);

    void deleteVacancies(Integer vacancyId, Vacancy vacancies);

    List<Vacancy> getAllVacanciesCategory(Integer categoryId, Vacancy vacancies);

    List<Vacancy> getAllVacanciesIsActive(Vacancy vacancies);
}
