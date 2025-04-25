package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.AccountType;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

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

    private Collection<GrantedAuthority> getAuthorities(AccountType accountType) {
        if (accountType == null) {
            throw new IllegalArgumentException("AccountType cannot be null");
        }
        return List.of(new SimpleGrantedAuthority(accountType.getType()));
    }
}
