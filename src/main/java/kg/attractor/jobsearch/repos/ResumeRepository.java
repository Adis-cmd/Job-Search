package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query(value = "select r from Resume r where r.category = :categoryId")
    List<Resume> findByCategoryId(Long categoryId);

    List<Resume> findAllResumeByApplicantId_Id(Long applicantId);

    @Query(value = "select r from Resume r where r.isActive = true")
    List<Resume> findAllResumeIsActive();

    //    @Query(value =  "update Resume r set r.name = ?, r.categoryId = ?, r.salary = ?, r.isActive = ?, r.updateTime = ? " +
    //            "where r.id = :resumeId")
    //    void editResume(Resume resume, Long resumeId);
}
