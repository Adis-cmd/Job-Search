package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.exception.NumberFormatException.ResumeServiceException;
import kg.attractor.jobsearch.exception.NumberFormatException.VacancyServiceException;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repos.RespondedApplicantRepository;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl extends MethodClass implements RespondedApplicantService {
    private final RespondedApplicantRepository repository;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean responseVacancies(Long vacancyId, Long resumeId) {
        Vacancy vacancy = getEntityOrThrow(vacancyService.findVacancyById(vacancyId),
                new VacancyServiceException("Vacancy not found"));

        Resume resume = getEntityOrThrow(resumeService.findResumeById(resumeId),
                new ResumeServiceException("Resume not found"));

        if (repository.existsByVacancyIdAndResumeId(vacancy, resume)) {
            return false;
        }

        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setVacancyId(vacancy);
        respondedApplicant.setResumeId(resume);
        respondedApplicant.setConfirmation(false);

        repository.saveAndFlush(respondedApplicant);
        return true;
    }

    @Override
    public List<Long> findRespondedResumeIdsByVacancy(Long vacancyId) {
        return repository.findResumeIdsByVacancyId(vacancyId);
    }

    @Override
    public Page<RespondedApplicantDto> getAllRespondedApplicants(Long userId, Pageable pageable) {
        Page<RespondedApplicant> respondedApplicants = repository.findAllRespondedApplicants(userId, pageable);
        return respondedApplicants.map(this::convertEntityToDto);
    }


    private RespondedApplicantDto convertEntityToDto(RespondedApplicant entity) {
        return modelMapper.map(entity, RespondedApplicantDto.class);
    }

}
