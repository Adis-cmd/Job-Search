package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.modal.EducationInfo;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceIml implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

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
