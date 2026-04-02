package com.universitymanagementproject.studentmanagementsystem.service;

import com.universitymanagementproject.studentmanagementsystem.entity.User;
import com.universitymanagementproject.studentmanagementsystem.repository.UserRepository;
import com.universitymanagementproject.studentmanagementsystem.exception.ResourceNotFoundException;
import com.universitymanagementproject.studentmanagementsystem.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.lang.NonNull; // Add this import
import java.util.Objects; // Add this import

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditService auditService;

    public User createUser(User user) {
        log.info("Creating new user: {}", user.getUsername());
        
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username already exists: " + user.getUsername());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already exists: " + user.getEmail());
        }

        user.setCreatedDate(LocalDateTime.now());
        user.setStatus(User.UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);
        
        // FIX: Ensure userId is not null for AuditService
        Long userId = Objects.requireNonNull(savedUser.getUserId(), "User ID must not be null after saving");
        auditService.logAction("User", userId, "CREATE", null, savedUser.toString());
        
        return savedUser;
    }

    public User updateUser(@NonNull Long id, User userDetails) { // FIX: Added @NonNull
        log.info("Updating user: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        String oldValues = user.toString();
        
        if (userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
        }
        if (userDetails.getStatus() != null) {
            user.setStatus(userDetails.getStatus());
        }

        User updatedUser = userRepository.save(user);
        
        // FIX: Pass the non-null id directly
        auditService.logAction("User", id, "UPDATE", oldValues, updatedUser.toString());
        
        return updatedUser;
    }

    public void deleteUser(@NonNull Long id) { // FIX: Added @NonNull
        log.info("Deleting user: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        auditService.logAction("User", id, "DELETE", user.toString(), null);
        userRepository.deleteById(id);
    }

    public User getUserById(@NonNull Long id) { // FIX: Added @NonNull
        log.info("Fetching user: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    public Page<User> getAllUsers(@NonNull Pageable pageable) { // FIX: Added @NonNull
        log.info("Fetching all users");
        return userRepository.findAll(pageable);
    }

    public Page<User> getUsersByRole(User.UserRole role, @NonNull Pageable pageable) { // FIX: Added @NonNull
        log.info("Fetching users with role: {}", role);
        return userRepository.findByRole(role, pageable); 
    }
}
