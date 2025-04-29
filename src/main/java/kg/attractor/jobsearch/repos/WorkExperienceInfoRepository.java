package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.WorkExperienceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Long> {

    @Query(value = "select * from work_experience_info where resumeid = :resumeId", nativeQuery = true)
    Page<WorkExperienceInfo> getWorkExperienceInfoByResumeId(@Param("resumeId") Long resumeId, Pageable pageable);
}
