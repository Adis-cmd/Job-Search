package kg.attractor.jobsearch.service;

import org.apache.catalina.User;

public interface RespondedApplicantService {
    void responseVacancies(Long vacancyId, User user);
}
