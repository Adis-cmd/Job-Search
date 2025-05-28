package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("""
            SELECT v,
                   (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) AS responseCount
            FROM Vacancy v
            WHERE v.isActive = true
            ORDER BY
                CASE WHEN :sort = 'createdAsc' THEN v.createdDate END ASC,
                CASE WHEN :sort = 'createdDesc' THEN v.createdDate END DESC,
                CASE WHEN :sort = 'salaryAsc' THEN v.salary END ASC,
                CASE WHEN :sort = 'salaryDesc' THEN v.salary END DESC,
                CASE WHEN :sort = 'responseCountAsc' THEN (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) END ASC,
                CASE WHEN :sort = 'responseCountDesc' THEN (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) END DESC,
                CASE WHEN :sort IS NULL THEN v.id END ASC
            """)
    Page<Object[]> findAllActiveVacanciesSorted(
            @Param("sort") String sort,
            Pageable pageable);


    @Query("""
            SELECT v,
                   (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) AS responseCount
            FROM Vacancy v
            WHERE v.isActive = true and
                        v.category.id = :categoryId
            ORDER BY
                CASE WHEN :sort = 'createdAsc' THEN v.createdDate END ASC,
                CASE WHEN :sort = 'createdDesc' THEN v.createdDate END DESC,
                CASE WHEN :sort = 'salaryAsc' THEN v.salary END ASC,
                CASE WHEN :sort = 'salaryDesc' THEN v.salary END DESC,
                CASE WHEN :sort = 'responseCountAsc' THEN (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) END ASC,
                CASE WHEN :sort = 'responseCountDesc' THEN (SELECT COUNT(ra) FROM RespondedApplicant ra WHERE ra.vacancyId = v) END DESC,
                CASE WHEN :sort IS NULL THEN v.id END ASC
            """)
    Page<Object[]> findAllActiveVacanciesSortedByCategoryId(
            @Param("sort") String sort,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

    @Query(value = "select v from Vacancy v where v.category = :categoryId")
    List<Vacancy> findVacanciesByCategoryId(Long categoryId);

    @Query(value = "select v from Vacancy v where v.isActive = true")
    List<Vacancy> findVacanciesByIsActive();

    @Query(value = "select v from Vacancy v where v.id = :vacancyId")
    Optional<Vacancy> findVacancyById(Long vacancyId);

    @Query(value = "SELECT COUNT(*) FROM vacancy v WHERE v.id = :vacancyId AND v.authorid = :userId", nativeQuery = true)
    Long countOwnedVacancy(Long vacancyId, Long userId);

    @Query(value = "select id from users u where u.email = ?", nativeQuery = true)
    Optional<Long> findUserIdByEmail(String email);

    Page<Vacancy> findAllResumeByAuthorId_Id(Long authorId, Pageable pageable);

    @Query(value = "SELECT v " +
            " FROM vacancy v " +
            "JOIN responded_applicant r ON r.vacancyId = v", nativeQuery = true)
    List<Vacancy> findAllVacancyByApplicant();

    Page<Vacancy> findByCategory(Category category, Pageable pageable);

    List<Vacancy> findByNameContainingIgnoreCase(String name);

    @Query(nativeQuery = true, value = "select * from vacancy v where v.authorId = :userId")
    List<Vacancy> findAllVacancyByUserId(Long userId);
}
