package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }
}
