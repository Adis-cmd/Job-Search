package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllResumes();

    void createResumes(ResumeDto resumesDto, String userId);

    void deleteResumes(String resumeId);

    ResumeDto getResumeById(String resumeId);

    void editResume(ResumeDto resumesDto, String resumeId);

    List<ResumeDto> getResumeByCategoryId(String categoryId);

    List<ResumeDto> getResumeByUserid(String userid);

    List<ResumeDto> getAllResumeIsActive();
}
