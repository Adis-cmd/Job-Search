package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumesDto;
import kg.attractor.jobsearch.modal.Resume;
import kg.attractor.jobsearch.service.ResumesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumesController {

    private final ResumesService resumesService;

    @GetMapping("search")
    public List<Resume> search() {
        return resumesService.getAllResumes();
    }

    @GetMapping("search/{categoryId}")
    public Resume searchCategory(@PathVariable("categoryId") ResumesDto resumes, int categoryId) {
        return resumesService.searchResumesCategoryId(resumes, categoryId);
    }

    @PostMapping("add")
    public Resume addResumes(@RequestBody ResumesDto resumesDto) {
        return resumesService.createResumes(resumesDto);
    }

    @PutMapping("update/{resumeId}")
    public Resume updateResumes(@PathVariable("resumeId") @RequestBody ResumesDto resumesDto, int resumeId) {
        return resumesService.editResumes(resumesDto, resumeId);
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus deleteResumes(@PathVariable("resumeId") int resumeId) {
        resumesService.deleteResumes(resumeId);
        return HttpStatus.OK;
    }

}
