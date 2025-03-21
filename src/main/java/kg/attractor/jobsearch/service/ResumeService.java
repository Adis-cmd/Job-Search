package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.modal.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> getAllResumes();

    Resume createResumes(ResumeDto resumesDto);

    Resume editResumes(ResumeDto resumesDto, int resumeId);

    void deleteResumes(int resumeId);

    List<ResumeDto> getResumeCategory(Long categoryId);

    List<ResumeDto> getResumeByUserid(Long userid);
}
