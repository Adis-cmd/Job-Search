package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<VacancyDto> getVacanciesByCategory(String categoryName, Pageable pageable);

    List<CategoryDto> getAllCategory();

     CategoryDto category(Category category);

    List<CategoryDto> findAllCategory();

    Category toCategoryEntity(CategoryDto dto);

    CategoryDto findCategoryById(Long id);

    List<CategoryDto> findCategoryByResume(Page<ResumeDto> resumes);

    List<CategoryDto> findCategoryByVacancy(Page<VacancyDto> vacancyDtoList);
}
