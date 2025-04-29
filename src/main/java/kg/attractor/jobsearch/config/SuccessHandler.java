package kg.attractor.jobsearch.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String role = authentication.getAuthorities().toString();

        if (role.contains("EMPLOYEE")) {
            response.sendRedirect("/resume");
        } else if (role.contains("APPLICANT")) {
            response.sendRedirect("/vacancy");
        } else {
            response.sendRedirect("/");
        }
    }
}
