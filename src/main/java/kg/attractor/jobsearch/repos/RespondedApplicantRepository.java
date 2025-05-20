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
            "ra.is_view, " +
            "ra.is_view_applicant, " +
            "ra.confirmation " +
            "from responded_applicant ra " +
            "LEFT JOIN resume r on r.id = ra.resumeId " +
            "LEFT JOIN vacancy v on v.id = ra.vacancyId " +
            "where r.applicantId = :userId",
            nativeQuery = true)
    Page<RespondedApplicant> findAllRespondedApplicants(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "select ra.id as respondedApplicantId, " +
            "r.id as resumeId, " +
            "v.id as vacancyId, " +
            "ra.is_view, " +
            "ra.is_view_applicant, " +
            "ra.confirmation " +
            "from responded_applicant ra " +
            "LEFT JOIN resume r on r.id = ra.resumeId " +
            "LEFT JOIN vacancy v on v.id = ra.vacancyId " +
            "where v.authorId = :userId",
            nativeQuery = true)
    Page<RespondedApplicant> findAllRespondedEmployee(@Param("userId") Long userId, Pageable pageable);


    @Query(value = "SELECT COUNT(*) FROM responded_applicant ra " +
            "LEFT JOIN vacancy v ON v.id = ra.vacancyId " +
            "WHERE v.authorId = :userId AND ra.is_view = false",
            nativeQuery = true)
    Long countUnviewedResponsesByEmployeeId(@Param("userId") Long userId);


    @Query(value = "SELECT COUNT(*) FROM responded_applicant ra " +
            "LEFT JOIN resume r ON r.id = ra.resumeId " +
            "WHERE r.applicantid = :userId AND ra.is_view_applicant = false",
            nativeQuery = true)
    Long countUnviewedResponsesByApplicantId(@Param("userId") Long userId);


}
