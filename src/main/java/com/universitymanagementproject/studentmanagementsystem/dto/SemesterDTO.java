package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemesterDTO {
    private Long semesterId;
    private String semesterCode;
    private String semesterName;
    private Integer semesterYear;
    private Integer semesterNumber;
    private Boolean isActive;
}