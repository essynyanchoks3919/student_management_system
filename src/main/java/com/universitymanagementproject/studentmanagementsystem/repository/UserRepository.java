package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);    
     
    Page<User> findByRole(User.UserRole role, org.springframework.data.domain.Pageable pageable);
    
    Page<User> findByStatus(User.UserStatus status, org.springframework.data.domain.Pageable pageable);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}