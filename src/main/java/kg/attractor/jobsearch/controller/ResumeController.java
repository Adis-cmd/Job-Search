package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
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
    public List<ResumeDto> search() {
        return resumesService.getAllResumes();
    }

    @PostMapping("add")
    public HttpStatus addResumes(@Valid @RequestBody ResumeDto resumesDto, @RequestParam(name = "userId")  Long userId) {
        resumesService.createResumes(resumesDto,  userId);
        return HttpStatus.CREATED;
    }

    @PutMapping("update/{resumeId}")
    public HttpStatus updateResumes(@Valid @PathVariable("resumeId") Long resumeId, @RequestBody ResumeDto resumesDto) {
        resumesService.editResume(resumesDto, resumeId);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{resumeId}")
    public HttpStatus deleteResumes(@PathVariable("resumeId") Long resumeId) {
        resumesService.deleteResumes(resumeId);
        return HttpStatus.OK;
    }

    @GetMapping("search/{categoryId}")
    public List<ResumeDto> searchResumesCategory(@PathVariable Long categoryId) {
        return resumesService.getResumeCategory(categoryId);
    }

    @GetMapping("user/{userId}")
    public List<ResumeDto> getAllResumesByUserId(@PathVariable Long userId) {
        return resumesService.getResumeByUserid(userId);
    }

    @GetMapping
    public ResumeDto getResumesById(@RequestParam(name = "id") Long id) {
        return resumesService.getResumeById(id);
    }

}
