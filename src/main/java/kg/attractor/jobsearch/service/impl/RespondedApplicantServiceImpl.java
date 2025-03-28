package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl extends MethodClass implements RespondedApplicantService {
    @Override
    public void responseVacancies(Long vacancyId , User user) {
        //TODO логика для отклика вакансии
    }
}
