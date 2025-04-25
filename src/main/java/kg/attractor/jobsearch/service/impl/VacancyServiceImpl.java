package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.CategoryNotFoundException;
import kg.attractor.jobsearch.exception.NoSuchElementException.UserNotFoundException;
import kg.attractor.jobsearch.exception.NoSuchElementException.VacancyNotFoundException;
import kg.attractor.jobsearch.exception.NumberFormatException.VacancyServiceException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repos.VacancyRepository;
import kg.attractor.jobsearch.service.CategoryService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl extends MethodClass implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserServiceImpl userService;
    @Lazy
    private final CategoryService categoryService;

    @Override
    public VacancyDto getVacancyById(String vacancyId) {
        log.info("Поиск вакансии с ID: {}", vacancyId);
        Long parceLong = parseId(vacancyId);
        Vacancy vacancy = getEntityOrThrow(vacancyRepository.findVacancyById(parceLong), new VacancyNotFoundException());
        log.info("Вакансия с ID: {} найдена", vacancyId);
        return vacancyDtos(vacancy);
    }

    @Override
    public void editVacancies(VacancyDto dto, String vacancyId, String email) {
        log.info("Редактирование вакансии с ID: {}", vacancyId);
        Long id = parseId(vacancyId);
        Long authorId = getEntityOrThrow(vacancyRepository.findUserIdByEmail(email),
                new UserNotFoundException());

        dto.setAuthorId(authorId);

        Vacancy vacancy = getOwnedVacancyOrThrow(id, email);
        validateVacancyDto(dto);
        updateVacancyFromDto(vacancy, dto);

        vacancyRepository.saveAndFlush(vacancy);
        log.info("Вакансия с ID: {} успешно обновлена", id);
    }


    private Vacancy getOwnedVacancyOrThrow(Long id, String email) {
        Long authorId = getEntityOrThrow(vacancyRepository.findUserIdByEmail(email),
                new UserNotFoundException());

        Long count = vacancyRepository.countOwnedVacancy(id, authorId);
        if (count <= 0) {
            throw new VacancyNotFoundException("Эта вакансия не принадлежит вам");
        }

        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("Вакансия не найдена по ID: " + id));
    }


    private void validateVacancyDto(VacancyDto dto) {
        if (dto.getCategoryId() == null || categoryService.countId(dto.getCategoryId()) <= 0) {
            throw new CategoryNotFoundException();
        }

        if (dto.getExpFrom() == null || dto.getExpFrom() < 0) {
            throw new VacancyServiceException("Начальный опыт работы не может быть отрицательным.");
        }

        if (dto.getExpTo() == null || dto.getExpTo() < 0) {
            throw new VacancyServiceException("Конечный опыт работы не может быть отрицательным.");
        }

        if (dto.getExpFrom() > dto.getExpTo()) {
            throw new VacancyServiceException("Начальный опыт не может быть больше конечного.");
        }
    }


    private void updateVacancyFromDto(Vacancy vacancy, VacancyDto dto) {
        Category category = categoryService.findById(dto.getCategoryId());

        vacancy.setName(dto.getName());

        vacancy.setDescription(dto.getDescription());
        vacancy.setCategory(category);
        vacancy.setSalary(dto.getSalary());
        vacancy.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setUpdatedTime(LocalDateTime.now());
    }


    @Override
    public long findCompanyByEmail(String email) {
        return getEntityOrThrow(vacancyRepository.findUserIdByEmail(email),
                new UserNotFoundException());
    }

    @Override
    public void createVacancies(VacancyDto vacanciesDto, Authentication authentication) {
        String userEmail = authentication.getName();
        log.info("Создание вакансии для автора с email: {}", userEmail);
        User user = userService.getUserByEmail(userEmail);
        Vacancy v = auxiliaryMethod(vacanciesDto);
        v.setAuthorId(user);
        v.setCreatedDate(LocalDateTime.now());
        v.setUpdatedTime(LocalDateTime.now());

        vacancyRepository.saveAndFlush(v);
        log.info("Вакансия успешно создана для автора с ID: {}", user.getId());
        //TODO логика для создании вакансии
    }

    private Vacancy auxiliaryMethod(VacancyDto vacanciesDto) {
        Vacancy v = new Vacancy();
        v.setName(vacanciesDto.getName());
        v.setDescription(vacanciesDto.getDescription());
        v.setCategory(categoryService.findById(vacanciesDto.getCategoryId()));
        v.setSalary(vacanciesDto.getSalary());
        v.setIsActive(vacanciesDto.getIsActive() != null ? vacanciesDto.getIsActive() : true);

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
    public Page<VacancyDto> getVacancies(Pageable pageable, String sort) {
        log.info("Запрос всех вакансий");
        Page<Object[]> result = vacancyRepository.findAllActiveVacanciesSorted(sort, pageable);
        if (result == null) {
            throw new VacancyNotFoundException();
        }
        log.info("Получено {} вакансий", result.getTotalElements());
        return result.map(row -> {
            Vacancy vacancy = (Vacancy) row[0];
            Long responseCount = ((Number) row[1]).longValue();
            return vacancyDtos(vacancy, responseCount);
        });
    }


    @Override
    public void deleteVacancies(String vacancyId, Authentication auth) {
        String email = auth.getName();
        log.info("Удаление вакансии с ID: {}", vacancyId);
        Long parceLong = parseId(vacancyId);

        Long authorId = getEntityOrThrow(vacancyRepository.findUserIdByEmail(email), new UserNotFoundException());

        if (vacancyRepository.countOwnedVacancy(parceLong, authorId) <= 0) {
            throw new VacancyNotFoundException("Эта вакансия не принадлежит вам");
        }

        vacancyRepository.deleteById(parceLong);
        log.info("Вакансия с ID: {} успешно удалена", vacancyId);
        //TODO логика для удаление вакансии по id
    }

    @Override
    public List<VacancyDto> getAllVacanciesCategory(String categoryId) {
        log.info("Поиск вакансий по категории с ID: {}", categoryId);
        Long parseCategoryId = parseId(categoryId);
        List<Vacancy> vacancies = vacancyRepository.findVacanciesByCategoryId(parseCategoryId);
        //TODO логика для поиска вакансии по категории
        return getVacancyDto(vacancies);
    }

    @Override
    public List<VacancyDto> getAllVacancyByResponded() {
        List<Vacancy> vacancies = vacancyRepository.findAllVacancyByApplicant();
        return getVacancyDto(vacancies);
    }

    @Override
    public Page<Vacancy> findByCategory(Category category, Pageable pageable) {
        return vacancyRepository.findByCategory(category, pageable);
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
        List<Vacancy> v = vacancyRepository.findVacanciesByIsActive();
        return getVacancyDto(v);
        //TODO логика для поиска всех активных вакансий
    }

    @Override
    public Page<VacancyDto> getVacancyByCreatorId(String creatorId, Pageable pageable) {
        Long parseCreatorId = parseId(creatorId);
        Page<Vacancy> v = vacancyRepository.findAllResumeByAuthorId_Id(parseCreatorId, pageable);
        return v.map(this::vacancyDtos);
    }

    private VacancyDto vacancyDtos(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategory() != null ? v.getCategory().getId() : null)
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.getIsActive())
                .authorId(v.getAuthorId() != null ? v.getAuthorId().getId() : null)
                .createdDate(v.getCreatedDate())
                .updatedTime(v.getUpdatedTime())
                .build();
    }

    private VacancyDto vacancyDtos(Vacancy vacancy, Long responseCount) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.getIsActive())
                .categoryId(vacancy.getCategory().getId())
                .authorId(vacancy.getAuthorId().getId())
                .createdDate(vacancy.getCreatedDate())
                .updatedTime(vacancy.getUpdatedTime())
                .responseCount(responseCount)
                .build();
    }


}
