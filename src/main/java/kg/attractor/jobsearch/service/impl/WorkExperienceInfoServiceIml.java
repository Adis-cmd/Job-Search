package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.WorkExperienceInfoException;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.repos.WorkExperienceInfoRepository;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceIml extends MethodClass implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;

    @Override
    public WorkExperienceInfoDto getWorkExperienceInfoById(String workId) {
        Long parse = parseId(workId);
        WorkExperienceInfo work =
                getEntityOrThrow(workExperienceInfoRepository.findById(parse),
                        new WorkExperienceInfoException());
        return workDto(work);
    }

    @Override
    public List<WorkExperienceInfoDto> getAllWorkExperienceInfo() {
        List<WorkExperienceInfo> weid = workExperienceInfoRepository.findAll();

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

        workExperienceInfoRepository.saveAndFlush(w);
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

    @Override
    public void saveWorkExperiences(ResumeDto dto, Resume resume) {
        if (dto.getWorkExperiences() == null) return;

        dto.getWorkExperiences().forEach(info -> {
            WorkExperienceInfo work = WorkExperienceInfo.builder()
                    .resumeId(resume)
                    .years(info.getYears())
                    .companyName(info.getCompanyName())
                    .position(info.getPosition())
                    .responsibilities(info.getResponsibilities())
                    .build();

            workExperienceInfoRepository.saveAndFlush(work);
        });
    }


    @Override
    public Page<WorkExperienceInfoDto> getWorkExperienceInfoByResumeId(Long resumeId, Pageable pageable) {
        Page<WorkExperienceInfo> workExperienceInfo = workExperienceInfoRepository.getWorkExperienceInfoByResumeId(resumeId, pageable);
        return workExperienceInfo.map(this::workDto);
    }
}