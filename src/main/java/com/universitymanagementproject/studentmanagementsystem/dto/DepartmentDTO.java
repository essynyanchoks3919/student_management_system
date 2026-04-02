package com.universitymanagementproject.studentmanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
    private Long departmentId;
    private String departmentCode;
    private String departmentName;
    private String description;
    private String building;
    private String officeNumber;
}