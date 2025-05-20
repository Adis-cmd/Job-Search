package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkExperienceInfoService {
    WorkExperienceInfoDto getWorkExperienceInfoById(String workId);

    List<WorkExperienceInfoDto> getAllWorkExperienceInfo();

    void createWorkExperienceInfo(WorkExperienceInfoDto wDto);

    void saveWorkExperiences(ResumeDto dto, Resume resume, User user);

    Page<WorkExperienceInfoDto> getWorkExperienceInfoByResumeId(Long resumeId, Pageable pageable);
}
