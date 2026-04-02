package com.universitymanagementproject.studentmanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. Allow static assets (CSS, JS, Images) for everyone
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                        // 2. Allow the login page
                        .requestMatchers("/login").permitAll()

                        // 3. Role-Based Access Control
                        .requestMatchers("/students/delete/**").hasRole("ADMIN")
                        .requestMatchers("/students/add/**", "/students/edit/**").hasAnyRole("ADMIN", "FACULTY")
                        .requestMatchers("/students/**").hasAnyRole("ADMIN", "FACULTY", "STUDENT")

                        // 4. Everything else requires login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")                // Point to your WebController @GetMapping("/login")
                        .loginProcessingUrl("/login")       // The URL Spring Security filters for POST data
                        .defaultSuccessUrl("/students", true)
                        .failureUrl("/login?error=true")    // Shows the error message in your UI
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // This replaces AntPathRequestMatcher
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    }
