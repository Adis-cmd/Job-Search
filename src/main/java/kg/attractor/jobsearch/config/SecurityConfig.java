package kg.attractor.jobsearch.config;

import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.impl.AuthUserDetailsService;
import kg.attractor.jobsearch.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SuccessHandler successHandler;
    private final AuthUserDetailsService authUserDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler(successHandler)
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/errors/error"))
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/vacancies/update/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/vacancies/delete/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/search").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/search/**").hasAuthority("APPLICANT")
                        .requestMatchers("/users/responses").hasAuthority("APPLICANT")
                        .requestMatchers("/users/applicant/**").hasAuthority("APPLICANT")
                        .requestMatchers("/resume").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/add").hasAuthority("APPLICANT")
                        .requestMatchers("/resumes/delete/**").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancies/isActive").hasAuthority("EMPLOYEE")
                        .requestMatchers("/vacancies/search/category/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/response/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/users/employee/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/company/info/**").hasAuthority("APPLICANT")
                        .requestMatchers("/company/**").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancy/info/**").authenticated()
                        .requestMatchers("/responded/**").authenticated()
                        .requestMatchers("/chat/**").authenticated()
                        .requestMatchers("/profile").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/auth/login")
                        .userInfoEndpoint(userConfig -> userConfig
                                .userService(customOAuth2UserService))
                        .successHandler(oAuthSuccessHandler));
        return http.build();
    }
}
