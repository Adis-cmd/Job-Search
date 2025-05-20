package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.RespondedApplicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RespondedApplicantService {
    Boolean responseVacancies(Long vacancyId, Long resumeId);

    List<Long> findRespondedResumeIdsByVacancy(Long vacancyId);

    Page<RespondedApplicantDto> getAllRespondedApplicants(Long userId, Pageable pageable);


    Page<RespondedApplicantDto> getAllRespondedEmployee(Long userId, Pageable pageable);

    RespondedApplicant findById(Long id);

    Long countRespondedEmployee(Long userId);

    Long countRespondedApplicants(Long userId);
}
