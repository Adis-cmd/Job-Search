package kg.attractor.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String fetch = "select email , password , enabled from users where email = ?";

        String fetchAccountType = "select email, type " +
                "from users u, account_type a " +
                "where email = ?" +
                "and a.id = u.accountType_id";


        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(fetch)
                .authoritiesByUsernameQuery(fetchAccountType);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/profile", true)
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/errors/error"))
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/vacancies/update/**").hasAuthority("1")
                        .requestMatchers("/vacancies/delete/**").hasAuthority("1")
                        .requestMatchers("/resumes/search").hasAuthority("1")
                        .requestMatchers("/resumes/search/**").hasAuthority("2")
                        .requestMatchers("/users/responses").hasAuthority("2")
                        .requestMatchers("/users/applicant/**").hasAuthority("2")
                        .requestMatchers("/resume").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/add").hasAuthority("APPLICANT")
                        .requestMatchers("/resumes/delete/**").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancies/isActive").hasAuthority("1")
                        .requestMatchers("/vacancies/search/category/**").hasAuthority("1")
                        .requestMatchers("/response/**").hasAuthority("1")
                        .requestMatchers("/users/employee/**").hasAuthority("1")
                        .requestMatchers("/profile").authenticated()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
