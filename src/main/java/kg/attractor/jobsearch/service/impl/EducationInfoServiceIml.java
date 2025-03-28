package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.exception.EducationInfoException;
import kg.attractor.jobsearch.modal.EducationInfo;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceIml implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public List<EducationInfoDto> getAllEducationInfo() {
        List<EducationInfo> ed = educationInfoDao.getAllEducationInfo();
        return ed.stream()
                .map(e -> EducationInfoDto.builder()
                        .id(e.getId())
                        .resumeId(e.getResumeId())
                        .institution(e.getInstitution())
                        .program(e.getProgram())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                        .degree(e.getDegree())
                        .build())
                .toList();
    }

    @Override
    public EducationInfoDto getEducationInfoById(Long educationInfoId) {
        EducationInfo educationInfo = educationInfoDao.getEducationInfoById(educationInfoId)
                .orElseThrow(EducationInfoException::new);

        return EducationInfoDto.builder()
                .id(educationInfo.getId())
                .resumeId(educationInfo.getResumeId())
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .startDate(educationInfo.getStartDate())
                .endDate(educationInfo.getEndDate())
                .degree(educationInfo.getDegree())
                .build();
    }

    @Override
    public void createEducationInfo(EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = new EducationInfo();
        educationInfo.setResumeId(educationInfoDto.getResumeId());
        educationInfo.setInstitution(educationInfoDto.getInstitution());
        educationInfo.setProgram(educationInfoDto.getProgram());
        educationInfo.setStartDate(educationInfoDto.getStartDate());
        educationInfo.setEndDate(educationInfoDto.getEndDate());
        educationInfo.setDegree(educationInfoDto.getDegree());

        educationInfoDao.createEducationInfo(educationInfo);
    }
}
