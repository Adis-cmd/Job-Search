package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("response")
@RequiredArgsConstructor
public class RespondedApplicantControllerApi {
    private final RespondedApplicantService respondedApplicantService;
//
//    @PostMapping("{vacancyId}")
//    public HttpStatus respondedApplicantService(@PathVariable("vacancyId") Long vacancyId,  User user) {
//        respondedApplicantService.responseVacancies(vacancyId, user);
//        return HttpStatus.OK;
//    }

}
