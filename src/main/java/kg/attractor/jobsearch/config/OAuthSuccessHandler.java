package kg.attractor.jobsearch.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.jobsearch.model.CustomOAuth2User;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.impl.AuthUserDetailsService;
import kg.attractor.jobsearch.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        authUserDetailsService.processOAuthPostLogin(
                oAuth2User.getAttribute("email"),
                oAuth2User.getAttribute("given_name"),
                oAuth2User.getAttribute("family_name"),
                oAuth2User.getAttribute("picture")
        );
        response.sendRedirect("/");
    }
}
