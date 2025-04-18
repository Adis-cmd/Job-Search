package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(value = "select v from Vacancy v")
    List<Vacancy> findAllVacancies();

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

    List<Vacancy> findAllResumeByAuthorId_Id(Long authorIdId);


    @Query(value = "SELECT v " +
            " FROM vacancy v " +
            "JOIN responded_applicant r ON r.vacancyId = v", nativeQuery = true)
    List<Vacancy> findAllVacancyByApplicant();

}
