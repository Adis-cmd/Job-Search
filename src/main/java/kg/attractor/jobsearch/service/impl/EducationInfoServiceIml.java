package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.NoSuchElementException.EducationInfoException;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.repos.EducationInfoRepository;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceIml extends MethodClass implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;

    @Override
    public List<EducationInfoDto> getAllEducationInfo() {
        List<EducationInfo> ed = educationInfoRepository.findAll();
        return eduDtoList(ed);
    }

    @Override
    public EducationInfoDto getEducationInfoById(String educationInfoId) {
        Long parseId = parseId(educationInfoId);
        EducationInfo educationInfo = getEntityOrThrow(educationInfoRepository.findById(parseId),
                new EducationInfoException());

        return eduDto(educationInfo);
    }


    @Override
    public void createEducationInfo(EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = new EducationInfo();
//        educationInfo.setResumeId(educationInfoDto.getResumeId());
        educationInfo.setInstitution(educationInfoDto.getInstitution());
        educationInfo.setProgram(educationInfoDto.getProgram());
        educationInfo.setStartDate(educationInfoDto.getStartDate());
        educationInfo.setEndDate(educationInfoDto.getEndDate());
        educationInfo.setDegree(educationInfoDto.getDegree());

        educationInfoRepository.saveAndFlush(educationInfo);
    }

    private List<EducationInfoDto> eduDtoList(List<EducationInfo> educationInfo) {
        return educationInfo.stream()
                .map(this::eduDto)
                .filter(Objects::nonNull)
                .toList();
    }

    private EducationInfoDto eduDto(EducationInfo educationInfo) {
        return EducationInfoDto.builder()
                .id(educationInfo.getId())
//                .resumeId(educationInfo.getResumeId())
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .startDate(educationInfo.getStartDate())
                .endDate(educationInfo.getEndDate())
                .degree(educationInfo.getDegree())
                .build();
    }

    @Override
    public void saveEducationInfos(ResumeDto dto, Resume resume) {
        if (dto.getEducationInfos() == null) return;

        dto.getEducationInfos().forEach(info -> {
            EducationInfo edu = EducationInfo.builder()
                    .resumeId(resume)
                    .institution(info.getInstitution())
                    .program(info.getProgram())
                    .startDate(info.getStartDate())
                    .endDate(info.getEndDate())
                    .degree(info.getDegree())
                    .build();

            educationInfoRepository.saveAndFlush(edu);

        });
    }
}
