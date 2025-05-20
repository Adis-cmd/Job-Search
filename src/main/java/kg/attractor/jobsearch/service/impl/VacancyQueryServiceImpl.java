package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repos.VacancyRepository;
import kg.attractor.jobsearch.service.VacancyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyQueryServiceImpl implements VacancyQueryService {

    private final VacancyRepository vacancyRepository;

    @Override
    public Page<Vacancy> findByCategory(Category category, Pageable pageable) {
        return vacancyRepository.findByCategory(category, pageable);
    }
}
