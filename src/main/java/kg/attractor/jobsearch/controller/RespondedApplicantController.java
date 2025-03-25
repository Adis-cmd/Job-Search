package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("response")
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;

    @PostMapping("{vacancyId}")
    public HttpStatus respondedApplicantService(@PathVariable("vacancyId") Long vacancyId, User user) {
        respondedApplicantService.responseVacancies(vacancyId, user);
        return HttpStatus.OK;
    }

}
