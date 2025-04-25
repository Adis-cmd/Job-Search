package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.model.Resume;

import java.util.List;

public interface WorkExperienceInfoService {
    WorkExperienceInfoDto getWorkExperienceInfoById(String workId);

    List<WorkExperienceInfoDto> getAllWorkExperienceInfo();

    void createWorkExperienceInfo(WorkExperienceInfoDto wDto);

    void saveWorkExperiences(ResumeDto dto, Resume resume);
}
