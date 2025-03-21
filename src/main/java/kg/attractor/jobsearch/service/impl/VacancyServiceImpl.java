package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;

    @Override
    public void editVacancies(VacancyDto vacanciesDto, int vacancyId) {
        //TODO логика для редактирование вакансии
    }

    @Override
    public void createVacancies(VacancyDto vacanciesDto) {
        //TODO логика для создании вакансии
    }


    @Override
    public List<VacancyDto> getVacancies() {
        List<Vacancy> vacancy = vacancyDao.getAllVacancies();
        return getVacancyDto(vacancy);
    }


    @Override
    public void deleteVacancies(Integer vacancyId, Vacancy vacancies) {
        //TODO логика для удаление вакансии по id
    }

    @Override
    public List<VacancyDto> getAllVacanciesCategory(Long categoryId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(categoryId);
        //TODO логика для поиска вакансии по категории
        return getVacancyDto(vacancies);
    }

    @Override
    public List<VacancyDto> getAllVacancyByResponded() {
        List<Vacancy> vacancies = vacancyDao.getVacancyByApplicant();
        return getVacancyDto(vacancies);
    }

    private List<VacancyDto> getVacancyDto(List<Vacancy> vacancies) {
        return vacancies.stream()
                .map(v -> {
                    try {
                        return VacancyDto.builder()
                                .id(v.getId())
                                .name(v.getName())
                                .description(v.getDescription())
                                .categoryId(v.getCategoryId())
                                .salary(v.getSalary())
                                .expFrom(v.getExpFrom())
                                .expTo(v.getExpTo())
                                .isActive(v.getIsActive())
                                .authorId(v.getAuthorId())
                                .createdDate(v.getCreatedDate())
                                .updatedTime(v.getUpdatedTime())
                                .build();
                    } catch (IllegalArgumentException e) {
                        System.err.println("Validation error for Vacancy: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }


    @Override
    public List<Vacancy> getAllVacanciesIsActive(Vacancy vacancies) {
        //TODO логика для поиска всех активных вакансий
        return List.of();
    }
}
