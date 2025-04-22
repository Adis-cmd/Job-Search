package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllResumes();

    void createResumes(ResumeDto resumesDto, String userId);

    void deleteResumes(String resumeId);

    ResumeDto getResumeById(String resumeId);

    void editResume(ResumeDto resumesDto, String resumeId);

    List<ResumeDto> getResumeByCategoryId(String categoryId);

    Page<ResumeDto> getResumeByUserid(String userid, Pageable pageable);

    Page<ResumeDto> getAllResumeIsActive(Pageable pageable);
}
