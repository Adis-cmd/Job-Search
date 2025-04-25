package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyQueryService {
    Page<Vacancy> findByCategory(Category category, Pageable pageable);
}
