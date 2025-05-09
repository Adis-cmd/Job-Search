package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.CategoryNotFoundException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repos.CategoryRepository;
import kg.attractor.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl extends MethodClass implements CategoryService {
    private final CategoryRepository repository;
    private final VacancyQueryService vacancyService;
    private final VacancyMapper vacancyMapper;

    @Override
    public Page<VacancyDto> getVacanciesByCategory(String categoryName, Pageable pageable) {
        Category category = getEntityOrThrow(repository.findByName(categoryName),
                new RuntimeException("Категория не найдена: " + categoryName));
        Page<Vacancy> vacancies = vacancyService.findByCategory(category, pageable);

        return vacancies.map(vacancyMapper::toDto);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = repository.findAll();
        return categoryDtoList(category);
    }

    private List<CategoryDto> categoryDtoList(List<Category> category) {
        return category.stream()
                .map(this::category)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public CategoryDto category(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> category = repository.findAll();
        return categoryDtoList(category);
    }

    @Override
    public Category toCategoryEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        Category categories = getEntityOrThrow(repository.findById(id),
                new CategoryNotFoundException());
        return category(categories);
    }

    @Override
    public List<CategoryDto> findCategoryByResume(Page<ResumeDto> resumes) {
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (ResumeDto resume : resumes) {
            Long id = resume.getCategoryId();
            if (id != null) {
                Optional<Category> category = repository.findById(id);
                category.ifPresent(c -> categoryDtos.add(category(c)));
            }
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> findCategoryByVacancy(Page<VacancyDto> vacancyDtoList) {
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (VacancyDto vacancyDto : vacancyDtoList) {
            Long id = vacancyDto.getCategoryId();
            if (id != null) {
                Optional<Category> category = repository.findById(id);
                category.ifPresent(c -> categoryDtos.add(category(c)));
            }
        }
        return categoryDtos;
    }

    @Override
    public Category findById(Long id) {
        return getEntityOrThrow(repository.findById(id), new CategoryNotFoundException());
    }

    @Override
    public Long countId(Long id) {
        return repository.countById(id);
    }
}
