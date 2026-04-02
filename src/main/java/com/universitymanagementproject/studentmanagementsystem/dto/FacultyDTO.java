package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDTO {
    private Long facultyId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String status;
    private Long departmentId;
    private String specialization;
    private String qualification;
    private String office;
    private String officeHours;
}