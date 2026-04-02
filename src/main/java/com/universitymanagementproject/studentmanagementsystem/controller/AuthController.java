package com.universitymanagementproject.studentmanagementsystem.controller;

import com.universitymanagementproject.studentmanagementsystem.entity.User;
import com.universitymanagementproject.studentmanagementsystem.security.JwtTokenProvider;
import com.universitymanagementproject.studentmanagementsystem.security.UserPrincipal;
import com.universitymanagementproject.studentmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        log.info("Login attempt for user: {}", username);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("type", "Bearer");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
//        log.info("Login attempt for user: {}", username);
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername());
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            response.put("type", "Bearer");
//            response.put("username", userPrincipal.getUsername());
//            response.put("email", userPrincipal.getEmail());
//            response.put("role", userPrincipal.getRole());
//
//            log.info("User {} logged in successfully", username);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            log.error("Login failed for user: {}", username);
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "Invalid credentials");
//            error.put("message", e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        log.info("Registration attempt for user: {}", user.getUsername());

        try {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Password is required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(User.UserStatus.ACTIVE);
            User savedUser = userService.createUser(user);

            String token = jwtTokenProvider.generateToken(savedUser.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("token", token);
            response.put("userId", savedUser.getUserId());
            response.put("username", savedUser.getUsername());
            response.put("email", savedUser.getEmail());

            log.info("User {} registered successfully", user.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Registration failed for user: {}", user.getUsername());
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Registration failed");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
        log.info("Validating token");

        Map<String, Object> response = new HashMap<>();
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            response.put("valid", true);
            response.put("username", username);
            return ResponseEntity.ok(response);
        } else {
            response.put("valid", false);
            response.put("message", "Invalid or expired token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
        log.info("Fetching current user");

        try {
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);
                String username = jwtTokenProvider.getUsernameFromToken(token);

                Map<String, Object> response = new HashMap<>();
                response.put("username", username);
                return ResponseEntity.ok(response);
            }

            Map<String, Object> error = new HashMap<>();
            error.put("error", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to fetch user");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}