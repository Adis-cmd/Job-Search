package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ContactInfoDao;
import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.NumberFormatException.ResumeServiceException;
import kg.attractor.jobsearch.model.ContactInfo;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl extends MethodClass implements ResumeService {

    private final ResumeDao resumeDao;
    private final ContactInfoDao contactInfoDao;
    private final EducationInfoDao educationInfoDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public List<ResumeDto> getAllResumes() {
        log.info("Запрос всех резюме");
        List<Resume> resumes = resumeDao.getAllResumes();
        return resumeDtos(resumes);
        //TODO логика для поиска всех резюме
    }

    @Override
    public void createResumes(ResumeDto resumesDto, String userId) {
        log.info("Создание резюме для пользователя с ID: {}", userId);
        Long userParse = parseId(userId);
        Resume resume = new Resume();
        resume.setName(resumesDto.getName());
        resume.setCategoryId(resumesDto.getCategoryId());
        resume.setSalary(resumesDto.getSalary());
        resume.setIsActive(resumesDto.getIsActive());

        Long resumeId = resumeDao.createResumes(resume, userParse);
        log.info("Резюме успешно создано с ID: {}", resumeId);


        if (resumesDto.getEducationInfos() != null) {
            resumesDto.getEducationInfos().forEach(educationInfoDto -> {
                EducationInfo edu = new EducationInfo();
                edu.setResumeId(resumeId);
                edu.setInstitution(educationInfoDto.getInstitution());
                edu.setProgram(educationInfoDto.getProgram());
                edu.setStartDate(educationInfoDto.getStartDate());
                edu.setEndDate(educationInfoDto.getEndDate());
                edu.setDegree(educationInfoDto.getDegree());
                log.debug("Добавлена информация об образовании: {}", edu);
                educationInfoDao.createEducationInfo(edu);
            });

            if (resumesDto.getWorkExperiences() != null) {
                resumesDto.getWorkExperiences().forEach(workExperienceInfoDto -> {
                    WorkExperienceInfo work = new WorkExperienceInfo();
                    work.setResumeId(resume.getId());
                    work.setYears(workExperienceInfoDto.getYears());
                    work.setCompanyName(workExperienceInfoDto.getCompanyName());
                    work.setPosition(workExperienceInfoDto.getPosition());
                    work.setResponsibilities(workExperienceInfoDto.getResponsibilities());
                    log.debug("Добавлен опыт работы: {}", work);
                    workExperienceInfoDao.createWorkExperienceInfo(work);
                });
            }

//            if (resumesDto.getContactInfos() != null) {
//                resumesDto.getContactInfos().forEach(contactInfoDto -> {
//                    ContactInfo contact = new ContactInfo();
//                    contact.setResumeId(resume.getId());
//                    contact.setTypeId(contactInfoDto.getTypeId());
//                    contact.setTypeId(contactInfoDto.getTypeId());
//                    contactInfoDao.addContactInfo(contact);
//                });
//            }
        }

        // TODO логика для создание резюме
    }


    @Override
    public void deleteResumes(String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Удаление резюме с ID: {}", parseResumeId);
        resumeDao.deleteResume(parseResumeId);
        //TODO логика для удаления резюме
    }


    @Override
    public ResumeDto getResumeById(String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Поиск резюме с ID: {}", parseResumeId);
        Resume resumes = getEntityOrThrow(resumeDao.getResumeById(parseResumeId),
                new ResumeServiceException("Не найденно резюме с таким id"));
        return resumeDtos(resumes);
        //TODO метод для поиска резюме по его id
    }

    @Override
    public void editResume(ResumeDto resumesDto, String resumeId) {
        Long parseResumeId = parseId(resumeId);
        log.info("Редактирование резюме с ID: {}", parseResumeId);
        Resume resume = new Resume();
        resume.setName(resumesDto.getName());
        resume.setCategoryId(resumesDto.getCategoryId());
        resume.setSalary(resumesDto.getSalary());
        resume.setIsActive(resumesDto.getIsActive());
        resumeDao.editResume(resume, parseResumeId);
        log.info("Резюме с ID: {} успешно обновлено", parseResumeId);
        //TODO логика для редактирование резюме
    }


    @Override
    public List<ResumeDto> getResumeCategory(String categoryId) {
        Long parseCategoryId = parseId(categoryId);
        log.info("Поиск резюме по категории с ID: {}", parseCategoryId);
        List<Resume> resume = resumeDao.getResume(parseCategoryId);
        return resumeDtos(resume);
        //TODO логика для поиска резюме по категории
    }


    @Override
    public List<ResumeDto> getResumeByUserid(String userid) {
        Long parseUserId = parseId(userid);
        log.info("Поиск резюме для пользователя с ID: {}", parseUserId);
        List<Resume> resume = resumeDao.getResumeByUser(parseUserId);
        return resumeDtos(resume);
        //TODO логика для поиска резюме по id пользователя
    }


    private ResumeDto resumeDtos(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
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
}
