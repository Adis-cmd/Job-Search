package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    Boolean existsByVacancyIdAndResumeId(Vacancy vacancyId, Resume resumeId);

    @Query("SELECT r.resumeId.id FROM RespondedApplicant r WHERE r.vacancyId.id = :vacancyId")
    List<Long> findResumeIdsByVacancyId(@Param("vacancyId") Long vacancyId);



    @Query(value = "select ra.id as respondedApplicantId, " +
            "r.id as resumeId, " +
            "v.id as vacancyId, " +
            "ra.confirmation " +
            "from responded_applicant ra " +
            "LEFT JOIN resume r on r.id = ra.resumeId " +
            "LEFT JOIN vacancy v on v.id = ra.vacancyId " +
            "where r.applicantId = :userId",
            nativeQuery = true)
    Page<RespondedApplicant> findAllRespondedApplicants(@Param("userId") Long userId, Pageable pageable);


}
