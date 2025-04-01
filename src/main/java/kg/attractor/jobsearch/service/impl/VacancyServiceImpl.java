package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.VacancyNotFoundException;
import kg.attractor.jobsearch.exception.NumberFormatException.VacancyServiceException;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl extends MethodClass implements VacancyService {

    private final VacancyDao vacancyDao;

    @Override
    public VacancyDto getVacancyById(String vacancyId) {
        Long parceLong = parseId(vacancyId);
        Vacancy vacancy = getEntityOrThrow(vacancyDao.getVacancyById(parceLong), new VacancyNotFoundException());
        return vacancyDtos(vacancy);
    }

    @Override
    public void editVacancies(VacancyDto vacanciesDto, String vacancyId) {
        Long parceLong = parseId(vacancyId);

        Vacancy v = auxiliaryMethod(vacanciesDto);

        vacancyDao.editVacancy(v, parceLong);
        //TODO логика для редактирование вакансии
    }

    @Override
    public void createVacancies(VacancyDto vacanciesDto, String authorId) {
        Long parceLong = parseId(authorId);
        Vacancy v = auxiliaryMethod(vacanciesDto);
        vacancyDao.createVacancy(v, parceLong);
        //TODO логика для создании вакансии
    }

    private Vacancy auxiliaryMethod(VacancyDto vacanciesDto) {
        Vacancy v = new Vacancy();
        v.setName(vacanciesDto.getName());
        v.setDescription(vacanciesDto.getDescription());
        v.setCategoryId(vacanciesDto.getCategoryId());
        v.setSalary(vacanciesDto.getSalary());
        if (vacanciesDto.getExpFrom() > vacanciesDto.getExpTo()) {
            throw new VacancyServiceException("Начало опыта работы не может быть больше конца.");
        }
        v.setExpFrom(vacanciesDto.getExpFrom());
        v.setExpTo(vacanciesDto.getExpTo());
        return v;
    }


    @Override
    public List<VacancyDto> getVacancies() {
        List<Vacancy> vacancy = vacancyDao.getAllVacancies();
        return getVacancyDto(vacancy);
    }


    @Override
    public void deleteVacancies(String vacancyId) {
        Long parceLong = parseId(vacancyId);
        vacancyDao.deleteVacancy(parceLong);
        //TODO логика для удаление вакансии по id
    }

    @Override
    public List<VacancyDto> getAllVacanciesCategory(String categoryId) {
        Long parseCategoryId = parseId(categoryId);
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(parseCategoryId);
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
                .map(this::vacancyDtos)
                .filter(Objects::nonNull)
                .toList();
    }


    @Override
    public List<VacancyDto> getAllVacanciesIsActive() {
        List<Vacancy> v = vacancyDao.getAllVacancyIsActive();
        return getVacancyDto(v);
        //TODO логика для поиска всех активных вакансий
    }

    private VacancyDto vacancyDtos(Vacancy v) {
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
    }

}
