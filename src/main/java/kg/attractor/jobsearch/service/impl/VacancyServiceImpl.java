package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.VacancyNotFoundException;
import kg.attractor.jobsearch.exception.NumberFormatException.VacancyServiceException;
import kg.attractor.jobsearch.modal.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl extends MethodClass implements VacancyService {

    private final VacancyDao vacancyDao;

    @Override
    public VacancyDto getVacancyById(String vacancyId) {
        log.info("Поиск вакансии с ID: {}", vacancyId);
        Long parceLong = parseId(vacancyId);
        Vacancy vacancy = getEntityOrThrow(vacancyDao.getVacancyById(parceLong), new VacancyNotFoundException());
        log.info("Вакансия с ID: {} найдена", vacancyId);
        return vacancyDtos(vacancy);
    }

    @Override
    public void editVacancies(VacancyDto vacanciesDto, String vacancyId, String email) {
        log.info("Редактирование вакансии с ID: {}", vacancyId);
        Long parceLong = parseId(vacancyId);

        Long authorId = vacancyDao.findCompanyByEmail(email);

        if (!vacancyDao.isVacancyOwnedByUser(parceLong, authorId)) {
            throw new VacancyNotFoundException("Эта вакансия не принадлежит вам");
        }

        Vacancy v = auxiliaryMethod(vacanciesDto);


        vacancyDao.editVacancy(v, parceLong);
        //TODO логика для редактирование вакансии
        log.info("Вакансия с ID: {} успешно обновлена", vacancyId);
    }

    @Override
    public void createVacancies(VacancyDto vacanciesDto, String authorId) {
        log.info("Создание вакансии для автор с ID: {}", authorId);
        Long parceLong = parseId(authorId);
        Vacancy v = auxiliaryMethod(vacanciesDto);
        vacancyDao.createVacancy(v, parceLong);
        log.info("Вакансия успешно создана для автора с ID: {}", authorId);
        //TODO логика для создании вакансии
    }

    private Vacancy auxiliaryMethod(VacancyDto vacanciesDto) {
        Vacancy v = new Vacancy();
        v.setName(vacanciesDto.getName());
        v.setDescription(vacanciesDto.getDescription());
        v.setCategoryId(vacanciesDto.getCategoryId());
        v.setSalary(vacanciesDto.getSalary());

        if (vacanciesDto.getExpFrom() == null || vacanciesDto.getExpFrom() < 0) {
            log.error("Ошибка: Начальный опыт работы не может быть отрицательным. ExpFrom: {}", vacanciesDto.getExpFrom());
            throw new VacancyServiceException("Начальный опыт работы не может быть отрицательным.");
        }

        if (vacanciesDto.getExpTo() == null || vacanciesDto.getExpTo() < 0) {
            log.error("Ошибка: Конечный опыт работы не может быть отрицательным. ExpTo: {}", vacanciesDto.getExpTo());
            throw new VacancyServiceException("Конечный опыт работы не может быть отрицательным.");
        }

        if (vacanciesDto.getExpFrom() > vacanciesDto.getExpTo()) {
            log.error("Ошибка: Начало опыта работы не может быть больше конца. ExpFrom: {}, ExpTo: {}", vacanciesDto.getExpFrom(), vacanciesDto.getExpTo());
            throw new VacancyServiceException("Начало опыта работы не может быть больше конца.");
        }
        v.setExpFrom(vacanciesDto.getExpFrom());
        v.setExpTo(vacanciesDto.getExpTo());
        log.debug("Создание вакансии: {}", v);
        return v;
    }


    @Override
    public List<VacancyDto> getVacancies() {
        log.info("Запрос всех вакансий");
        List<Vacancy> vacancy = vacancyDao.getAllVacancies();
        return getVacancyDto(vacancy);
    }


    @Override
    public void deleteVacancies(String vacancyId, String email) {
        log.info("Удаление вакансии с ID: {}", vacancyId);
        Long parceLong = parseId(vacancyId);

        Long authorId = vacancyDao.findCompanyByEmail(email);

        if (!vacancyDao.isVacancyOwnedByUser(parceLong, authorId)) {
            throw new VacancyNotFoundException("Эта вакансия не принадлежит вам");
        }

        vacancyDao.deleteVacancy(parceLong);
        log.info("Вакансия с ID: {} успешно удалена", vacancyId);
        //TODO логика для удаление вакансии по id
    }

    @Override
    public List<VacancyDto> getAllVacanciesCategory(String categoryId) {
        log.info("Поиск вакансий по категории с ID: {}", categoryId);
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
        log.info("Поиск всех активных вакансий");
        List<Vacancy> v = vacancyDao.getAllVacancyIsActive();
        return getVacancyDto(v);
        //TODO логика для поиска всех активных вакансий
    }

    @Override
    public List<VacancyDto> getVacancyByCreatorId(String creatorId) {
        Long parseCreatorId = parseId(creatorId);
        List<Vacancy> v = vacancyDao.getVacancyByCreatorId(parseCreatorId);
        return getVacancyDto(v);
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
