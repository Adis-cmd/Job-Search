package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.WorkExperienceInfoException;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceIml extends MethodClass implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public WorkExperienceInfoDto getWorkExperienceInfoById(String workId) {
        Long parse = parseId(workId);
        WorkExperienceInfo work =
                getEntityOrThrow(workExperienceInfoDao.getWorkExperienceInfoById(parse),
                        new WorkExperienceInfoException());
        return workDto(work);
    }

    @Override
    public List<WorkExperienceInfoDto> getAllWorkExperienceInfo() {
        List<WorkExperienceInfo> weid = workExperienceInfoDao.getAllWorkExperienceInfo();

        return workListToDto(weid);
    }


    @Override
    public void createWorkExperienceInfo(WorkExperienceInfoDto wDto) {
        WorkExperienceInfo w = new WorkExperienceInfo();
//        w.setResumeId(wDto.getResumeId());
        w.setYears(wDto.getYears());
        w.setCompanyName(wDto.getCompanyName());
        w.setPosition(wDto.getPosition());
        w.setResponsibilities(wDto.getResponsibilities());

        workExperienceInfoDao.createWorkExperienceInfo(w);
    }

    private WorkExperienceInfoDto workDto(WorkExperienceInfo work) {
        return WorkExperienceInfoDto.builder()
                .id(work.getId())
//                .resumeId(work.getResumeId())
                .years(work.getYears())
                .companyName(work.getCompanyName())
                .position(work.getPosition())
                .responsibilities(work.getResponsibilities())
                .build();
    }

    private List<WorkExperienceInfoDto> workListToDto(List<WorkExperienceInfo> list) {
        return list.stream()
                .map(this::workDto)
                .filter(Objects::nonNull)
                .toList();
    }
}