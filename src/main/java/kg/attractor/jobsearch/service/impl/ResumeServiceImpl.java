package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.CategoryNotFoundException;
import kg.attractor.jobsearch.exception.NoSuchElementException.UserNotFoundException;
import kg.attractor.jobsearch.exception.NumberFormatException.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.NumberFormatException.ResumeServiceException;
import kg.attractor.jobsearch.model.*;
import kg.attractor.jobsearch.repos.*;
import kg.attractor.jobsearch.service.ContactInfoService;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl extends MethodClass implements ResumeService {

    private final ResumeDao resumeDao;
    private final EducationInfoRepository educationInfoRepository;
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository resumeCategoryRepository;
    private final ContactInfoService  contactInfoService;
    private final ContactInfoRepository contactInfoRepository;
    @Override
    public List<ResumeDto> getAllResumes() {
        log.info("Запрос всех резюме");
        List<Resume> resumes = resumeRepository.findAll();
        return resumeDtos(resumes);
        //TODO логика для поиска всех резюме
    }

    @Override
    public void createResumes(ResumeDto resumesDto, String userId) {
        log.info("Создание резюме для пользователя с ID: {}", userId);
        Long userParse = parseId(userId);

        Resume resume = Resume.builder()
                .name(resumesDto.getName())
                .salary(resumesDto.getSalary())
                .isActive(resumesDto.getIsActive())
                .category(getEntityOrThrow(resumeCategoryRepository.findById(resumesDto.getCategoryId()),
                        new CategoryNotFoundException()))
                .applicant(getEntityOrThrow(userRepository.findById(userParse),
                        new UserNotFoundException()))
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        Resume savedResume = resumeRepository.save(resume);

        log.info("Резюме успешно создано с ID: {}", savedResume.getId());

        saveEducationInfos(resumesDto, savedResume);
        saveWorkExperiences(resumesDto, savedResume);
        saveContactInfos(resumesDto, savedResume);
    }



    private Resume buildResumeEntity(ResumeDto dto) {
        return Resume.builder()
                .name(dto.getName())
                .category(getEntityOrThrow(
                        resumeCategoryRepository.findById(dto.getCategoryId()),
                        new CategoryNotFoundException()))
                .salary(dto.getSalary())
                .isActive(dto.getIsActive())
                .build();
    }

    private void saveEducationInfos(ResumeDto dto, Resume resume) {
        if (dto.getEducationInfos() == null) return;

        dto.getEducationInfos().forEach(info -> {
            EducationInfo edu = EducationInfo.builder()
                    .resumeId(resume)
                    .institution(info.getInstitution())
                    .program(info.getProgram())
                    .startDate(info.getStartDate())
                    .endDate(info.getEndDate())
                    .degree(info.getDegree())
                    .build();

            log.debug("Добавлена информация об образовании: {}", edu);
            educationInfoRepository.saveAndFlush(edu);

        });
    }

    private void saveWorkExperiences(ResumeDto dto, Resume resume) {
        if (dto.getWorkExperiences() == null) return;

        dto.getWorkExperiences().forEach(info -> {
            WorkExperienceInfo work = WorkExperienceInfo.builder()
                    .resumeId(resume)
                    .years(info.getYears())
                    .companyName(info.getCompanyName())
                    .position(info.getPosition())
                    .responsibilities(info.getResponsibilities())
                    .build();

            log.debug("Добавлен опыт работы: {}", work);
            workExperienceInfoRepository.saveAndFlush(work);
        });
    }

    private void saveContactInfos(ResumeDto dto, Resume resume) {
        if (dto.getContactInfos() == null) return;
        dto.getContactInfos().forEach(info -> {
                ContactInfo contact = ContactInfo.builder()
                        .resumeId(resume)
                        .typeId(contactInfoService.toContactTypeEntity(info.getTypeId()))
                        .value(info.getValue())
                        .build();
                log.debug("Добавлен контакт: {}", contact);
                contactInfoRepository.saveAndFlush(contact);
        });
    }


    @Override
    public void deleteResumes(String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Удаление резюме с ID: {}", parseResumeId);
        resumeRepository.deleteById(parseResumeId);
        //TODO логика для удаления резюме
    }


    @Override
    public ResumeDto getResumeById(String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Поиск резюме с ID: {}", parseResumeId);
        Resume resumes = getEntityOrThrow(resumeRepository.findById(parseResumeId),
                new ResumeServiceException("Не найденно резюме с таким id"));
        return resumeDtos(resumes);
        //TODO метод для поиска резюме по его id
    }

    @Override
    public void editResume(ResumeDto resumesDto, String resumeId) {
        Long parsedResumeId = parseId(resumeId);
        log.info("Редактирование резюме с ID: {}", parsedResumeId);

        Resume existingResume = getEntityOrThrow( resumeRepository.findById(parsedResumeId),
                new ResumeServiceException("Резюме с ID " + parsedResumeId + " не найдено"));

        existingResume.setName(resumesDto.getName());
        existingResume.setCategory(getEntityOrThrow(
                resumeCategoryRepository.findById(resumesDto.getCategoryId()),
                new CategoryNotFoundException()
        ));
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
