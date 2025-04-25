package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.model.Resume;

import java.util.List;

public interface EducationInfoService {
    List<EducationInfoDto> getAllEducationInfo();

    EducationInfoDto getEducationInfoById(String educationInfoId);

    void createEducationInfo(EducationInfoDto educationInfoDto);

    void saveEducationInfos(ResumeDto dto, Resume resume);
}
