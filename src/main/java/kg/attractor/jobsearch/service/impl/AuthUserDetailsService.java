package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.AccountType;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repos.UserRepository;
import kg.attractor.jobsearch.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountTypeService accountTypeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("{user.service.notFound}"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                Collections.singleton(new SimpleGrantedAuthority(user.getAccountType().getType()))
        );
    }

    public void processOAuthPostLogin(String email, String name, String surname, String avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oAuth2Token) {
            Map<String, Object> attributes = oAuth2Token.getPrincipal().getAttributes();

            AuthUserDetailsService.log.info("OAuth2 attributes: {}", attributes);

            var user = userRepository.findByEmail(email);

            if (user.isEmpty()) {
                var users = new User();
                users.setEmail(email);
                users.setPassword(passwordEncoder.encode("qwe"));
                users.setAccountType(accountTypeService.findById(3L));
                users.setEnabled(true);
                users.setName(name);
                users.setSurname(surname);
                users.setPhoneNumber("+996 500 000 000");
                users.setAge(null);
                users.setAvatar(avatar);
                userRepository.saveAndFlush(users);
            }
        }
    }

}
