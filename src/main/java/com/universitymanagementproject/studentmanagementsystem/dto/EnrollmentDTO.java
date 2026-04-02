package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long studentId;
    private Long courseOfferingId;
    private LocalDateTime enrollmentDate;
    private String status;
    private Integer attendancePercentage;
}