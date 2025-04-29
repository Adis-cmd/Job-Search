package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.EducationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {

    @Query(value = "select * from education_info where resumeId = :resumeId", nativeQuery = true)
    Page<EducationInfo> findByResumeId(@Param("resumeId") Long resumeId, Pageable pageable);
}
