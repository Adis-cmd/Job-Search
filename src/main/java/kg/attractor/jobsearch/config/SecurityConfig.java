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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .passwordEncoder(passwordEncoder)
                .authoritiesByUsernameQuery(fetchAccountType);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/vacancies").permitAll()
                        .requestMatchers("/vacancies/add").hasAuthority("EMPLOYEE")
                        .requestMatchers("/vacancies/update/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/vacancies/delete/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/search").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/search/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/users/responses").hasAuthority("EMPLOYEE")
                        .requestMatchers("/users/applicant/**").hasAuthority("EMPLOYEE")
                        .requestMatchers("/resumes/add").hasAuthority("APPLICANT")
                        .requestMatchers("/resumes/update/**").hasAuthority("APPLICANT")
                        .requestMatchers("/resumes/delete/**").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancies/isActive").hasAuthority("APPLICANT")
                        .requestMatchers("/vacancies/search/category/**").hasAuthority("APPLICANT")
                        .requestMatchers("/response/**").hasAuthority("APPLICANT")
                        .requestMatchers("/users/employee/**").hasAuthority("APPLICANT")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
