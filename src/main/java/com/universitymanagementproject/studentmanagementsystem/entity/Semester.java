package com.universitymanagementproject.studentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "semesters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long semesterId;

    @Column(unique = true, nullable = false, length = 20)
    private String semesterCode;

    @Column(nullable = false, length = 200)
    private String semesterName;

    private Integer semesterYear;
    private Integer semesterNumber;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Builder.Default
    private Boolean isActive = false;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<CourseOffering> courseOfferings = new HashSet<>();

  }