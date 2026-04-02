package com.universitymanagementproject.studentmanagementsystem.controller;

import java.util.List;
import java.util.Objects; // Required for Option 2

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universitymanagementproject.studentmanagementsystem.entity.Enrollment;
import com.universitymanagementproject.studentmanagementsystem.service.EnrollmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/enrollments")
@Slf4j
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseOfferingId) {
        log.info("Enrolling student {} in course offering {}", studentId, courseOfferingId);
        // Validating both IDs satisfy the @NonNull requirement
        return ResponseEntity.status(HttpStatus.CREATED).body(
            enrollmentService.enrollStudent(
                Objects.requireNonNull(studentId), 
                Objects.requireNonNull(courseOfferingId)
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        log.info("Fetching enrollment: {}", id);
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(Objects.requireNonNull(id)));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable Long studentId) {
        log.info("Fetching enrollments for student: {}", studentId);
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(Objects.requireNonNull(studentId)));
    }

    @GetMapping("/course-offering/{courseOfferingId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable Long courseOfferingId) {
        log.info("Fetching enrollments for course offering: {}", courseOfferingId);
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(Objects.requireNonNull(courseOfferingId)));
    }

    @PostMapping("/{id}/drop")
    public ResponseEntity<Enrollment> dropCourse(@PathVariable Long id) {
        log.info("Dropping course for enrollment: {}", id);
        return ResponseEntity.ok(enrollmentService.dropCourse(Objects.requireNonNull(id)));
    }

    @PutMapping("/{id}/attendance")
    public ResponseEntity<String> updateAttendance(@PathVariable Long id, @RequestParam Integer percentage) {
        log.info("Updating attendance for enrollment: {}", id);
        // Percentage is kept as Integer, but the ID is guarded
        enrollmentService.updateAttendance(Objects.requireNonNull(id), percentage);
        return ResponseEntity.ok("Attendance updated successfully");
    }
}
