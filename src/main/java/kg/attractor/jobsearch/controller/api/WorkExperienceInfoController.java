package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("workExperience")
@RequiredArgsConstructor
public class WorkExperienceInfoController {
    private final WorkExperienceInfoService  workExperienceInfoService;


    @GetMapping("{workId}")
    public  WorkExperienceInfoDto getWorkExperienceInfoById(@PathVariable String workId) {
        return workExperienceInfoService.getWorkExperienceInfoById(workId);
    }

    @GetMapping
    public List<WorkExperienceInfoDto> getAllWorkExperienceInfo(){
        return  workExperienceInfoService.getAllWorkExperienceInfo();
    }
}
