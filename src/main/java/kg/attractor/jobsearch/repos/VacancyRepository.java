package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT COUNT(v) FROM Vacancy v WHERE v.id = :vacancyId AND v.authorId = :userId")
    Boolean isVacancyOwnedByUser(Long vacancyId, Long userId);

    @Query(value = "select u.id from User u where u.email = :email")
    Long findCompanyByEmail(String email);

    List<Vacancy> findAllResumeByAuthorId_Id(Long authorIdId);


    @Query(value = "SELECT v " +
            " FROM vacancy v " +
            "JOIN responded_applicant r ON r.vacancyId = v", nativeQuery = true)
    List<Vacancy> findAllVacancyByApplicant();

}
