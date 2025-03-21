package kg.attractor.jobsearch.service;

import org.apache.catalina.User;

public interface RespondedApplicantService {
    void responseVacancies(Integer vacancyId, User user);
}
