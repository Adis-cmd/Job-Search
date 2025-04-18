package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Query(value = "select a from AccountType a where a.id = :id")
    Optional<AccountType> findByType(Long id);
}
