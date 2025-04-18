package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT COUNT(c) FROM Category c WHERE c.id = :id")
    Long countById(Long id);
}
