package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private Integer credits;
    private Integer capacity;
    private Integer currentEnrollment;
    private Long departmentId;
}