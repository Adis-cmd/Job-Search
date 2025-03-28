package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {
    List<EducationInfoDto> getAllEducationInfo();

    EducationInfoDto getEducationInfoById(Long educationInfoId);

    void createEducationInfo(EducationInfoDto educationInfoDto);
}
