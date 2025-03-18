package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ResumesDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.modal.Resumes;
import kg.attractor.jobsearch.service.ResumesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumesServiceImpl implements ResumesService {

    private List<Resumes> resumes;


    @Override
    public List<Resumes> getAllResumes() {
        //TODO логика для поиска резюме
        return List.of();
    }

    @Override
    public Resumes searchResumesCategoryId(ResumesDto resumes, int categoryId) {
        //TODO логика для поиска резюме по его id
        return null;
    }



    @Override
    public Resumes createResumes(ResumesDto resumesDto) {
        Resumes newResume = Resumes.builder()
                .id(resumes.size() + 1)
                .applicantId(resumesDto.getApplicantId())
                .name(resumesDto.getName())
                .categoryId(resumesDto.getCategoryId())
                .salary(resumesDto.getSalary())
                .isActive(resumesDto.isActive())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        resumes.add(newResume);
        return newResume;
    }

    @Override
    public Resumes editResumes(ResumesDto resumesDto, int resumeId) {
        Resumes updateResumes = resumes.stream()
                .filter(resume -> resume.getId() == resumeId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("resume not found"));

        updateResumes.setName(resumesDto.getName());
        updateResumes.setCategoryId(resumesDto.getCategoryId());
        updateResumes.setSalary(resumesDto.getSalary());
        updateResumes.setActive(resumesDto.isActive());
        updateResumes.setUpdateTime(LocalDateTime.now());
        return updateResumes;
    }

    @Override
    public void deleteResumes(int resumeId) {
        Resumes deleteResumes = resumes
                .stream()
                .filter(resumesDelete -> resumesDelete.getId() == resumeId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("resume not found"));

        resumes.remove(deleteResumes);
    }
}
