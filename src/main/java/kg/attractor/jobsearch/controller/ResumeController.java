package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.modal.Resume;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumesService;

    @GetMapping("search")
    public List<Resume> search() {
        return resumesService.getAllResumes();
    }

    @PostMapping("add")
    public Resume addResumes(@RequestBody ResumeDto resumesDto) {
        return resumesService.createResumes(resumesDto);
    }

    @PutMapping("update/{resumeId}")
    public Resume updateResumes(@PathVariable("resumeId") @RequestBody ResumeDto resumesDto, int resumeId) {
        return resumesService.editResumes(resumesDto, resumeId);
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus deleteResumes(@PathVariable("resumeId") int resumeId) {
        resumesService.deleteResumes(resumeId);
        return HttpStatus.OK;
    }

    @GetMapping("search/{categoryId}")
    public List<ResumeDto> searchResumesCategory(@PathVariable Long categoryId) {
        return resumesService.getResumeCategory(categoryId);
    }

    @GetMapping("user/{userId}")
    public List<ResumeDto>  getAllResumesByUserId(@PathVariable Long userId) {
        return resumesService.getResumeByUserid(userId);
    }

}
