package com.employees.services;

import com.employees.models.UserA;
import com.employees.repositories.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserA user = userRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException("User Not Found with -> username or email : " + email)
            );

        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(new ArrayList<>()) 
            .build();
    }
}
