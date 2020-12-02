package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mailEuroavia) {
        Optional<User> userOptional = userRepository.findByMailEuroavia(mailEuroavia);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with mailEuroavia: " + mailEuroavia));

        return new org.springframework.security
                .core.userdetails.User(user.getMailEuroavia(), user.getPassword(),
                user.isEnabled(), true, true,
                true, getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
