package com.universitymanagementproject.studentmanagementsystem.config;

import com.universitymanagementproject.studentmanagementsystem.entity.User;
import com.universitymanagementproject.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========================================");
        System.out.println("🔧 USER INITIALIZER STARTING...");
        System.out.println("========================================\n");

        // Create Admin User
        try {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(User.UserRole.ADMIN);
                admin.setStatus(User.UserStatus.ACTIVE);
                userRepository.save(admin);
                System.out.println("✅ ADMIN USER CREATED!");
                System.out.println("   Username: admin");
                System.out.println("   Password: admin123");
                System.out.println("   Role: ADMIN");
                System.out.println("   Status: ACTIVE\n");
            } else {
                System.out.println("ℹ️ Admin user already exists\n");
            }
        } catch (Exception e) {
            System.err.println("❌ Error creating admin user: " + e.getMessage());
            e.printStackTrace();
        }

        // Create Faculty User
        try {
            if (userRepository.findByUsername("faculty").isEmpty()) {
                User faculty = new User();
                faculty.setUsername("faculty");
                faculty.setEmail("faculty@example.com");
                faculty.setPassword(passwordEncoder.encode("faculty123"));
                faculty.setRole(User.UserRole.FACULTY);
                faculty.setStatus(User.UserStatus.ACTIVE);
                userRepository.save(faculty);
                System.out.println("✅ FACULTY USER CREATED!");
                System.out.println("   Username: faculty");
                System.out.println("   Password: faculty123");
                System.out.println("   Role: FACULTY");
                System.out.println("   Status: ACTIVE\n");
            } else {
                System.out.println("ℹ️ Faculty user already exists\n");
            }
        } catch (Exception e) {
            System.err.println("❌ Error creating faculty user: " + e.getMessage());
        }

        // Create Student User
        try {
            if (userRepository.findByUsername("student").isEmpty()) {
                User student = new User();
                student.setUsername("student");
                student.setEmail("student@example.com");
                student.setPassword(passwordEncoder.encode("student123"));
                student.setRole(User.UserRole.STUDENT);
                student.setStatus(User.UserStatus.ACTIVE);
                userRepository.save(student);
                System.out.println("✅ STUDENT USER CREATED!");
                System.out.println("   Username: student");
                System.out.println("   Password: student123");
                System.out.println("   Role: STUDENT");
                System.out.println("   Status: ACTIVE\n");
            } else {
                System.out.println("ℹ️ Student user already exists\n");
            }
        } catch (Exception e) {
            System.err.println("❌ Error creating student user: " + e.getMessage());
        }

        // List all existing users
        System.out.println("========================================");
        System.out.println("📋 ALL USERS IN DATABASE:");
        System.out.println("========================================");
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            System.out.println("❌ No users found in database!");
        } else {
            for (User user : allUsers) {
                System.out.println("ID: " + user.getUserId() +
                        " | Username: " + user.getUsername() +
                        " | Email: " + user.getEmail() +
                        " | Role: " + user.getRole() +
                        " | Status: " + user.getStatus());
                System.out.println("   Password Hash: " + user.getPassword());
            }
        }
        System.out.println("========================================\n");
    }
}