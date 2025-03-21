package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.modal.Resume;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;

    @Override
    public List<Resume> getAllResumes() {
        //TODO логика для поиска резюме
        return List.of();
    }

    @Override
    public Resume createResumes(ResumeDto resumesDto) {
        // TODO логика для создание резюме
        return null;
    }

    @Override
    public Resume editResumes(ResumeDto resumesDto, int resumeId) {
        //TODO логика для редактирование резюме
        return null;
    }

    @Override
    public void deleteResumes(int resumeId) {
        //TODO логика для удаления резюме
    }


    @Override
    public List<ResumeDto> getResumeCategory(Long categoryId) {
        List<Resume> resume = resumeDao.getResume(categoryId);

        return resumeDtos(resume);
    }

    @Override
    public List<ResumeDto> getResumeByUserid(Long userid) {
        List<Resume> resume = resumeDao.getResumeByUser(userid);
        return resumeDtos(resume);
    }

    private List<ResumeDto> resumeDtos(List<Resume> resume) {
        return resume.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .applicantId(e.getApplicantId())
                        .name(e.getName())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .isActive(e.getIsActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }
}
