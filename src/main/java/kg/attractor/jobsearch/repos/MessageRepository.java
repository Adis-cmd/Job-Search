package kg.attractor.jobsearch.repos;

import kg.attractor.jobsearch.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRespondedApplicantsId(Long respondedApplicantId);


}
