package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumesDto;
import kg.attractor.jobsearch.modal.Resume;

import java.util.List;

public interface ResumesService {
    List<Resume> getAllResumes();

    Resume searchResumesCategoryId(ResumesDto resumes, int categoryId);

    Resume createResumes(ResumesDto resumesDto);

    Resume editResumes(ResumesDto resumesDto, int resumeId);

    void deleteResumes(int resumeId);
}
