package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.exception.EducationInfoException;
import kg.attractor.jobsearch.modal.EducationInfo;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceIml extends MethodClass implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public List<EducationInfoDto> getAllEducationInfo() {
        List<EducationInfo> ed = educationInfoDao.getAllEducationInfo();
        return eduDtoList(ed);
    }

    @Override
    public EducationInfoDto getEducationInfoById(String educationInfoId) {
        Long parseId = parseId(educationInfoId);
        EducationInfo educationInfo = getEntityOrThrow(educationInfoDao.getEducationInfoById(parseId),
                new EducationInfoException());

        return eduDto(educationInfo);
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

    private List<EducationInfoDto> eduDtoList(List<EducationInfo> educationInfo) {
        return educationInfo.stream()
                .map(this::eduDto)
                .filter(Objects::nonNull)
                .toList();
    }

    private EducationInfoDto eduDto(EducationInfo educationInfo) {
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
}
