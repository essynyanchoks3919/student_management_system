package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseOfferingDTO {
    private Long courseOfferingId;
    private Long courseId;
    private Long semesterId;
    private Long facultyId;
    private Integer enrolledCount;
}