package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT COUNT(c) FROM Category c WHERE c.id = :id")
    Long countById(Long id);

    @Query(value = "select * from category where name = :name", nativeQuery = true)
    Optional<Category> findByName(String name);


}
