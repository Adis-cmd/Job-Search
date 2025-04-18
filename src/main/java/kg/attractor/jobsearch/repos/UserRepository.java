package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users u where u.email = ?", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE :name ")
    List<User> findAllByName(String name);

    @Query(value = "select u from User u where u.phoneNumber = :phoneNumber")
    Optional<User> findUserByPhoneNumber(String phoneNumber);


    @Query(value = "select count(u) from User u where u.email = :email")
    Boolean userExistsByEmail(String email);

    @Query(value = "select u.id from User u where u.email = :email")
    Long findUSerByEmail(String email);

    @Query(value =  """
    SELECT u
    FROM User u
    LEFT JOIN Resume r ON r.applicant = u
    LEFT JOIN RespondedApplicant a ON a.resumeId = r
    LEFT JOIN Vacancy v ON a.vacancyId = v
    WHERE a.confirmation = true
    """)
    List<User> getUserByResponse();
}
