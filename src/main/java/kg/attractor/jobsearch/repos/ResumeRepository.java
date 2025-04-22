package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query(value = "select r from Resume r where r.category = :categoryId")
    List<Resume> findByCategoryId(Long categoryId);

    Page<Resume> findAllResumeByApplicantId_Id(Long applicantId, Pageable pageable);

    @Query(value = "select r from Resume r where r.isActive = true")
    Page<Resume> findAllResumeIsActive(Pageable pageable);

    //    @Query(value =  "update Resume r set r.name = ?, r.categoryId = ?, r.salary = ?, r.isActive = ?, r.updateTime = ? " +
    //            "where r.id = :resumeId")
    //    void editResume(Resume resume, Long resumeId);
}
