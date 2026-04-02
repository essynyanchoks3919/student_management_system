package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String role;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;
}