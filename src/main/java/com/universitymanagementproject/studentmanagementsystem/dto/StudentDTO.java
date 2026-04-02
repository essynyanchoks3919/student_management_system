package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private LocalDate enrollmentDate;
    private String status;
    private Long departmentId;
    private Double cgpa;
    private Integer totalCreditsCompleted;
}