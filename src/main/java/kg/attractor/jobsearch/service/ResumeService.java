package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.modal.Resume;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllResumes();

    void createResumes(ResumeDto resumesDto, Long userId);

    void deleteResumes(Long resumeId);

    List<ResumeDto> getResumeById(Long resumeId);

    void editResume(ResumeDto resumesDto, Long resumeId);

    List<ResumeDto> getResumeCategory(Long categoryId);

    List<ResumeDto> getResumeByUserid(Long userid);
}
