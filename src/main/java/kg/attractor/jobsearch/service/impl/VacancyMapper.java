package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.stereotype.Component;

@Component
public class VacancyMapper {
    public VacancyDto toDto(Vacancy v) {
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
}
