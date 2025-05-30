package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<ResumeDto> getAllResumes();

    void createResumes(ResumeDto resumesDto, String userId);

    void deleteResumes(String resumeId);

    ResumeDto getResumeById(String resumeId, Principal principal);

    ResumeDto updateTime(Long resumeId);

    Optional<Resume> findResumeById(Long resumeId);

    void editResume(ResumeDto resumesDto, String resumeId);

    List<ResumeDto> getResumeByCategoryId(String categoryId);

    Page<ResumeDto> getResumeByUserid(String userid, Pageable pageable);


    Page<ResumeDto> getAllResumeIsActive(Pageable pageable);
}
