package kg.attractor.jobsearch.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        if (user != null && user.getLanguage() != null) {
            Locale userLocale = Locale.forLanguageTag(user.getLanguage());
            localeResolver.setLocale(request, response, userLocale);
        }

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
