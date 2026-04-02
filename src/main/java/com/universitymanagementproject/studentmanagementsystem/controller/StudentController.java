package com.universitymanagementproject.studentmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universitymanagementproject.studentmanagementsystem.entity.Student;
import com.universitymanagementproject.studentmanagementsystem.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        log.info("Creating new student");
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("Fetching student: {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        log.info("Fetching all students");
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        log.info("Updating student: {}", id);
        return ResponseEntity.ok(studentService.updateStudent(id, studentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("Deleting student: {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        log.info("Searching students");
        return ResponseEntity.ok(studentService.searchStudents(firstName, lastName, email));
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<?> getStudentEnrollments(@PathVariable Long id) {
        log.info("Fetching enrollments for student: {}", id);
        return ResponseEntity.ok(studentService.getStudentEnrollments(id));
    }

    @GetMapping("/{id}/cgpa")
    public ResponseEntity<Double> calculateCGPA(@PathVariable Long id) {
        log.info("Calculating CGPA for student: {}", id);
        return ResponseEntity.ok(studentService.calculateCGPA(id));
    }

    @PostMapping("/import-batch")
    public ResponseEntity<List<Student>> importStudents(@RequestBody List<Student> students) {
        log.info("Importing batch of students");
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.importStudentsBatch(students));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Student>> getStudentsByDepartment(@PathVariable Long departmentId) {
        log.info("Fetching students by department: {}", departmentId);
        return ResponseEntity.ok(studentService.getStudentsByDepartment(departmentId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Student> changeStudentStatus(@PathVariable Long id, @RequestParam String status) {
        log.info("Changing student status: {}", id);
        return ResponseEntity.ok(studentService.changeStudentStatus(id, Student.StudentStatus.valueOf(status)));
    }
}