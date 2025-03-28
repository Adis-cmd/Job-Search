package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exception.WorkExperienceInfoException;
import kg.attractor.jobsearch.modal.WorkExperienceInfo;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceIml implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public WorkExperienceInfoDto getWorkExperienceInfoById(Long workId) {
        WorkExperienceInfo work = workExperienceInfoDao.getWorkExperienceInfoById(workId)
                .orElseThrow(WorkExperienceInfoException::new);


        return WorkExperienceInfoDto.builder()
                .id(work.getId())
                .resumeId(work.getResumeId())
                .years(work.getYears())
                .companyName(work.getCompanyName())
                .position(work.getPosition())
                .responsibilities(work.getResponsibilities())
                .build();
    }

    @Override
    public List<WorkExperienceInfoDto> getAllWorkExperienceInfo() {
        List<WorkExperienceInfo> weid = workExperienceInfoDao.getAllWorkExperienceInfo();

        return weid.stream()
                .map(w -> WorkExperienceInfoDto.builder()
                        .id(w.getId())
                        .resumeId(w.getResumeId())
                        .years(w.getYears())
                        .companyName(w.getCompanyName())
                        .position(w.getPosition())
                        .responsibilities(w.getResponsibilities())
                        .build())
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
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
