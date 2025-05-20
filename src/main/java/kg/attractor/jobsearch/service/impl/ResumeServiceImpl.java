package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exception.NumberFormatException.ResumeServiceException;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.repos.ResumeRepository;
import kg.attractor.jobsearch.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl extends MethodClass implements ResumeService {
    private final EducationInfoService educationInfoService;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ResumeRepository resumeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ContactInfoService contactInfoService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final MessageSource messageSource;

    @Override
    public List<ResumeDto> getAllResumes() {
        log.info("Запрос всех резюме");
        List<Resume> resumes = resumeRepository.findAll();
        return resumeDtos(resumes);
        //TODO логика для поиска всех резюме
    }

    @Override
    @Transactional
    public void createResumes(ResumeDto resumesDto, String userId) {
        log.info("Создание резюме для пользователя с ID: {}", userId);
        Long userParse = parseId(userId);

        if (hasValidWorkExperiences(resumesDto)) {
            for (WorkExperienceInfoDto workExperienceInfoDto : resumesDto.getWorkExperiences()) {
                int currentYear = LocalDate.now().getYear();
                Integer workYear = workExperienceInfoDto.getYears();
                if (workYear == null || workYear < 0 || workYear > currentYear || workYear < (currentYear - (userService.findById(userParse).getAge() - 18))) {
                    throw new
                            IllegalArgumentException(
                                    String.format("Некорректный год работы: %d. Убедитесь, что это не раньше достижения 18 лет, не в будущем и не отрицательное.",
                                            workYear)
                    );
                }
            }
        }


        if (hasValidEducationInfos(resumesDto)) {
            int currentYear = LocalDate.now().getYear();

            for (EducationInfoDto e : resumesDto.getEducationInfos()) {
                if (e.getStartDate() == null || e.getEndDate() == null) {
                    throw new IllegalArgumentException("Дата начала и окончания обучения обязательны");
                }

                int startYear = e.getStartDate().getYear();
                int endYear = e.getEndDate().getYear();

                if (startYear < 0) {
                    throw new IllegalArgumentException("Год начала обучения не может быть отрицательным");
                }
                if (endYear < 0) {
                    throw new IllegalArgumentException("Год окончания обучения не может быть отрицательным");
                }
                if (startYear < 1900) {
                    throw new IllegalArgumentException("Год начала обучения не может быть раньше 1900");
                }
                if (startYear > currentYear) {
                    throw new IllegalArgumentException("Год начала обучения не может быть в будущем");
                }
                if (endYear < startYear) {
                    throw new IllegalArgumentException("Год окончания обучения не может быть раньше года начала");
                }
                if (endYear > currentYear) {
                    throw new IllegalArgumentException("Год окончания обучения не может быть в будущем");
                }
            }
        }


        Resume resume = Resume.builder()
                .name(resumesDto.getName())
                .salary(resumesDto.getSalary())
                .isActive(resumesDto.getIsActive())
                .category(categoryService.findById(resumesDto.getCategoryId()))
                .applicant(userService.findById(userParse))
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        Resume savedResume = resumeRepository.save(resume);

        log.info("Резюме успешно создано с ID: {}", savedResume.getId());

        if (hasValidEducationInfos(resumesDto)) {
            List<EducationInfoDto> educationInfos = resumesDto.getEducationInfos();
            for (EducationInfoDto educationInfoDto : educationInfos) {
                EducationInfo educationInfo = modelMapper.map(educationInfoDto, EducationInfo.class);
                educationInfo.setResumeId(savedResume);
                educationInfoService.saveEducationInfos(resumesDto, savedResume);            }
        }

        if (hasValidWorkExperiences(resumesDto)) {
            List<WorkExperienceInfoDto> workExperiences = resumesDto.getWorkExperiences();
            for (WorkExperienceInfoDto workExperienceInfoDto : workExperiences) {
                WorkExperienceInfo workExperience = modelMapper.map(workExperienceInfoDto, WorkExperienceInfo.class);
                workExperience.setResumeId(savedResume);
                workExperienceInfoService.saveWorkExperiences(resumesDto, savedResume, userService.findById(userParse));
            }
        }


        if (hasValidContactInfos(resumesDto)) {
            contactInfoService.saveContactInfos(resumesDto, savedResume);
        }
    }

    private boolean hasValidEducationInfos(ResumeDto dto) {
        return dto.getEducationInfos() != null
                && dto.getEducationInfos().stream()
                .anyMatch(info -> info.getInstitution() != null && !info.getInstitution().isBlank());
    }

    private boolean hasValidWorkExperiences(ResumeDto dto) {
        return dto.getWorkExperiences() != null
                && dto.getWorkExperiences().stream()
                .anyMatch(info -> info.getCompanyName() != null && !info.getCompanyName().isBlank());
    }

    private boolean hasValidContactInfos(ResumeDto dto) {
        return dto.getContactInfos() != null
                && dto.getContactInfos().stream()
                .anyMatch(info -> info.getValue() != null && !info.getValue().isBlank());
    }

    @Override
    public void deleteResumes(String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Удаление резюме с ID: {}", parseResumeId);
        resumeRepository.deleteById(parseResumeId);
        //TODO логика для удаления резюме
    }


    @Override
    public ResumeDto getResumeById(String resumeId, Principal p) {

        if (p == null) {
            throw new UserServiceException("Вам запрещен доступ для этой страницы");
        }

        User user = userService.getUserByEmail(p.getName());
        Long parseResumeId = parseId(resumeId);

        log.info("Поиск резюме с ID: {}", parseResumeId);

        Resume resume = resumeRepository.findById(parseResumeId)
                .orElseThrow(() -> new ResumeServiceException(
                        messageSource.getMessage("resume.service.notFoundById", null, Locale.getDefault())
                ));

        if (!user.getAccountType().getType().equals("EMPLOYEE") &&
                !user.getId().equals(resume.getApplicant().getId())) {
            throw new ResumeServiceException(
                    messageSource.getMessage("resume.info.view.valid", null, Locale.getDefault())
            );
        }

        return resumeDtos(resume);
    }

    @Override
    public Optional<Resume> findResumeById(Long resumeId) {
        Resume resumes = getEntityOrThrow(resumeRepository.findById(resumeId),
                new ResumeServiceException("{resume.service.notFoundById}"));
        return Optional.of(resumes);
    }


    @Override
    @Transactional
    public void editResume(ResumeDto resumesDto, String resumeId) {
        Long parsedResumeId = parseId(resumeId);
        log.info("Редактирование резюме с ID: {}", parsedResumeId);

        Resume existingResume = getEntityOrThrow(resumeRepository.findById(parsedResumeId),
                new ResumeServiceException("{resume.service.notFoundById}"));

        existingResume.setName(resumesDto.getName());
        existingResume.setCategory(categoryService.findById(resumesDto.getCategoryId()));
        existingResume.setSalary(resumesDto.getSalary());
        existingResume.setIsActive(resumesDto.getIsActive());
        resumeRepository.saveAndFlush(existingResume);

        log.info("Резюме с ID: {} успешно обновлено", parsedResumeId);
    }


    @Override
    public List<ResumeDto> getResumeByCategoryId(String categoryId) {
        Long parseCategoryId = parseId(categoryId);
        log.info("Поиск резюме по категории с ID: {}", parseCategoryId);
        List<Resume> resume = resumeRepository.findByCategoryId(parseCategoryId);
        return resumeDtos(resume);
        //TODO логика для поиска резюме по категории
    }


    @Override
    public Page<ResumeDto> getResumeByUserid(String userid, Pageable pageable) {
        Long parseUserId = parseId(userid);
        log.info("Поиск резюме для пользователя с ID: {}", parseUserId);
        Page<Resume> resume = resumeRepository.findAllResumeByApplicantId_Id(parseUserId, pageable);
        return resume.map(this::resumeDtos);
        //TODO логика для поиска резюме по id пользователя
    }

    private ResumeDto resumeDtos(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicant().getId())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }

    private List<ResumeDto> resumeDtos(List<Resume> resume) {
        return resume.stream()
                .map(this::resumeDtos)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Page<ResumeDto> getAllResumeIsActive(Pageable pageable) {
        Page<Resume> resumes = resumeRepository.findAllResumeIsActive(pageable);

        return resumes.map(this::resumeDtos);
    }

}
