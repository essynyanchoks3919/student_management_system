package com.universitymanagementproject.studentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StudentStatus status = StudentStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Enrollment> enrollments = new HashSet<>();

    @Column(columnDefinition = "DECIMAL(5,2) DEFAULT 0.0")
    @Builder.Default
    private Double cgpa = 0.0;

    @Column(columnDefinition = "INT DEFAULT 0")
    @Builder.Default
    private Integer totalCreditsCompleted = 0;

    @Column(columnDefinition = "INT DEFAULT 0")
    @Builder.Default
    private Integer totalCreditsEnrolled = 0;

    public enum StudentStatus {
        ACTIVE, INACTIVE, SUSPENDED, GRADUATED, DROPPED, ON_LEAVE
    }
}