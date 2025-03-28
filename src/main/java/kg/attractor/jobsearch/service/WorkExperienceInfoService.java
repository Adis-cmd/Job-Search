package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceInfoService {
    WorkExperienceInfoDto getWorkExperienceInfoById(Long workId);

    List<WorkExperienceInfoDto> getAllWorkExperienceInfo();

    void createWorkExperienceInfo(WorkExperienceInfoDto wDto);
}
