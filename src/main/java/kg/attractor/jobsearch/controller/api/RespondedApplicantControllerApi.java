package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("response")
@RequiredArgsConstructor
public class RespondedApplicantControllerApi {
    private final RespondedApplicantService respondedApplicantService;
    private final UserService userService;

    @PostMapping("/update-view")
    public ResponseEntity<String> updateView() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserEmail(currentUserEmail);

//        if (currentUser.getAccountType() == 1) {
//         respondedApplicantService.
//        }
        return ResponseEntity.ok("Отклики обновлены");    }
}
