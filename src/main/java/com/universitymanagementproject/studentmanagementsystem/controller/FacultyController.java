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
import org.springframework.web.bind.annotation.RestController;

import com.universitymanagementproject.studentmanagementsystem.entity.Faculty;
import com.universitymanagementproject.studentmanagementsystem.service.FacultyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/faculty")
@Slf4j
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        log.info("Creating new faculty");
        return ResponseEntity.status(HttpStatus.CREATED).body(facultyService.createFaculty(faculty));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        log.info("Fetching faculty: {}", id);
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Faculty>> getAllFaculty(Pageable pageable) {
        log.info("Fetching all faculty");
        return ResponseEntity.ok(facultyService.getAllFaculty(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails) {
        log.info("Updating faculty: {}", id);
        return ResponseEntity.ok(facultyService.updateFaculty(id, facultyDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        log.info("Deleting faculty: {}", id);
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Faculty>> getFacultyByDepartment(@PathVariable Long departmentId) {
        log.info("Fetching faculty for department: {}", departmentId);
        return ResponseEntity.ok(facultyService.getFacultyByDepartment(departmentId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Faculty> getFacultyByEmail(@PathVariable String email) {
        log.info("Fetching faculty by email: {}", email);
        return ResponseEntity.ok(facultyService.getFacultyByEmail(email));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Faculty>> getFacultyByStatus(@PathVariable String status) {
        log.info("Fetching faculty with status: {}", status);
        return ResponseEntity.ok(facultyService.getFacultyByStatus(Faculty.FacultyStatus.valueOf(status)));
    }
}