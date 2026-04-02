package com.universitymanagementproject.studentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
//import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.*;
//import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(unique = true, nullable = false, length = 20)
    private String departmentCode;

    @Column(nullable = false, length = 200)
    private String departmentName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String building;
    private String officeNumber;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Faculty> faculty = new HashSet<>();
}
