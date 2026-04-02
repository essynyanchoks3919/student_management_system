package com.universitymanagementproject.studentmanagementsystem.security;

import com.universitymanagementproject.studentmanagementsystem.entity.User;
import com.universitymanagementproject.studentmanagementsystem.repository.UserRepository;
import com.universitymanagementproject.studentmanagementsystem.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    @SuppressWarnings("null") // Suppresses the warning for findByUsername return type
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user details for username: {}", username);
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.create(Objects.requireNonNull(user, "User must not be null"));
    }

    @Transactional
    @SuppressWarnings("null") // This removes the warning from the userRepository.findById line
    public UserDetails loadUserById(Long id) {
        log.info("Loading user details for id: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return UserPrincipal.create(Objects.requireNonNull(user, "User must not be null"));
    }
}
