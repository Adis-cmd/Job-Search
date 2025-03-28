package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.modal.EducationInfo;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("educationInfo")
@RequiredArgsConstructor
public class EducationInfoController {
    private final EducationInfoService educationInfoService;

    @GetMapping("{educationInfoId}")
    public EducationInfoDto getEducationIngoById(@PathVariable String educationInfoId) {
        return  educationInfoService.getEducationInfoById(educationInfoId);
    }
    @GetMapping
    public List<EducationInfoDto> getAllEducationInfo(){
        return educationInfoService.getAllEducationInfo();
    }
}
