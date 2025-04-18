package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.CategoryDao;
import kg.attractor.jobsearch.dto.CategoryDto;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl  implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = categoryDao.getAllCategory();
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
    public Category toCategoryEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
