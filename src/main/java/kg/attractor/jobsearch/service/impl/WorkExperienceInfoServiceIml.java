package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.modal.WorkExperienceInfo;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceIml implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    public void createWorkExperienceInfo(WorkExperienceInfoDto wDto) {
        WorkExperienceInfo w = new WorkExperienceInfo();
        w.setResumeId(wDto.getResumeId());
        w.setYears(wDto.getYears());
        w.setCompanyName(wDto.getCompanyName());
        w.setPosition(wDto.getPosition());
        w.setResponsibilities(wDto.getResponsibilities());

        workExperienceInfoDao.createWorkExperienceInfo(w);
    }
}
