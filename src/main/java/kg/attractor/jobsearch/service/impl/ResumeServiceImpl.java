package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.ResumeServiceException;
import kg.attractor.jobsearch.modal.EducationInfo;
import kg.attractor.jobsearch.modal.Resume;
import kg.attractor.jobsearch.modal.WorkExperienceInfo;
import kg.attractor.jobsearch.service.EducationInfoService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final EducationInfoDao educationInfoDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        return resumeDtos(resumes);
        //TODO логика для поиска всех резюме
    }

    @Override
    public void createResumes(ResumeDto resumesDto, Long userId) {
        Resume resume = new Resume();
        resume.setName(resumesDto.getName());
        resume.setCategoryId(resumesDto.getCategoryId());
        resume.setSalary(resumesDto.getSalary());
        resume.setIsActive(resumesDto.getIsActive());

        Long resumeId =  resumeDao.createResumes(resume, userId);

        if (resumesDto.getEducationInfos() != null) {
            resumesDto.getEducationInfos().forEach(educationInfoDto -> {
                EducationInfo edu = new EducationInfo();
                edu.setResumeId(resumeId);
                edu.setInstitution(educationInfoDto.getInstitution());
                edu.setProgram(educationInfoDto.getProgram());
                edu.setStartDate(educationInfoDto.getStartDate());
                edu.setEndDate(educationInfoDto.getEndDate());
                edu.setDegree(educationInfoDto.getDegree());
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
                    workExperienceInfoDao.createWorkExperienceInfo(work);
                });
            }
        }

        // TODO логика для создание резюме
    }


    @Override
    public void deleteResumes(Long resumeId) {
        resumeDao.deleteResume(resumeId);
        //TODO логика для удаления резюме
    }


    @Override
    public ResumeDto getResumeById(Long resumeId) {
        Resume resumes = resumeDao.getResumeById(resumeId).orElseThrow(() -> new ResumeServiceException("Не найденно резюме с таким id"));
        return resumeDtos(resumes);
        //TODO метод для поиска резюме по его id
    }

    @Override
    public void editResume(ResumeDto resumesDto, Long resumeId) {
        Resume resume = new Resume();
        resume.setName(resumesDto.getName());
        resume.setCategoryId(resumesDto.getCategoryId());
        resume.setSalary(resumesDto.getSalary());
        resume.setIsActive(resumesDto.getIsActive());
        resumeDao.editResume(resume, resumeId);
        //TODO логика для редактирование резюме
    }


    @Override
    public List<ResumeDto> getResumeCategory(Long categoryId) {
        List<Resume> resume = resumeDao.getResume(categoryId);
        return resumeDtos(resume);
        //TODO логика для поиска резюме по категории
    }


    @Override
    public List<ResumeDto> getResumeByUserid(Long userid) {
        List<Resume> resume = resumeDao.getResumeByUser(userid);
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
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .applicantId(e.getApplicantId())
                        .name(e.getName())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .isActive(e.getIsActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }
}
