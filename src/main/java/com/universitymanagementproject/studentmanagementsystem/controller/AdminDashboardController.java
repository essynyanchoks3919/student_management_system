package com.universitymanagementproject.studentmanagementsystem.controller;

import com.universitymanagementproject.studentmanagementsystem.entity.Student;
import com.universitymanagementproject.studentmanagementsystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Admin Dashboard Controller
 * Handles Thymeleaf template rendering for admin pages
 * 
 * @author Esther
 * @version 1.0.0
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminDashboardController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * Admin Dashboard Page
     * GET /admin/
     * 
     * @param model Spring Model for template variables
     * @return dashboard.html template name
     */
    @GetMapping("/")
    public String dashboard(Model model) {
        log.info("Loading admin dashboard");
        
        try {
            // Add dashboard metrics
            model.addAttribute("totalStudents", 1245);
            model.addAttribute("activeEnrollments", 890);
            model.addAttribute("totalCourses", 45);
            model.addAttribute("totalFaculty", 120);
            
            // Add recent students
            Pageable pageable = PageRequest.of(0, 5);
            Page<Student> recentStudents = studentService.getAllStudents(pageable);
            model.addAttribute("recentStudents", recentStudents.getContent());
            
            return "admin/dashboard";
        } catch (Exception e) {
            log.error("Error loading dashboard", e);
            return "error/500";
        }
    }
    
    /**
     * Student Management Page
     * GET /admin/students
     * 
     * @param page Page number (default: 1)
     * @param model Spring Model for template variables
     * @return students/list.html template name
     */
    @GetMapping("/students")
    public String studentsList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        log.info("Loading students list, page: {}", page);
        
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Student> studentsPage = studentService.getAllStudents(pageable);
            
            model.addAttribute("students", studentsPage.getContent());
            model.addAttribute("page", page + 1);
            model.addAttribute("totalPages", studentsPage.getTotalPages());
            model.addAttribute("totalStudents", studentsPage.getTotalElements());
            
            return "admin/students/list";
        } catch (Exception e) {
            log.error("Error loading students list", e);
            return "error/500";
        }
    }
    
    /**
     * Create New Student Page
     * GET /admin/students/create
     * 
     * @param model Spring Model
     * @return students/form.html template name
     */
    @GetMapping("/students/create")
    public String createStudent(Model model) {
        log.info("Loading create student form");
        model.addAttribute("student", null);
        return "admin/students/form";
    }
    
    /**
     * Edit Student Page
     * GET /admin/students/{id}/edit
     * 
     * @param id Student ID
     * @param model Spring Model
     * @return students/form.html template name
     */
    @GetMapping("/students/{id}/edit")
    public String editStudent(@PathVariable Long id, Model model) {
        log.info("Loading edit student form for ID: {}", id);
        
        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "admin/students/form";
        } catch (Exception e) {
            log.error("Student not found: {}", id);
            return "error/404";
        }
    }
    
    /**
     * View Student Details
     * GET /admin/students/{id}
     * 
     * @param id Student ID
     * @param model Spring Model
     * @return students/view.html template name
     */
    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        log.info("Loading student view for ID: {}", id);
        
        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "admin/students/view";
        } catch (Exception e) {
            log.error("Student not found: {}", id);
            return "error/404";
        }
    }
    
    /**
     * Import Students Page
     * GET /admin/students/import
     * 
     * @return students/import.html template name
     */
    @GetMapping("/students/import")
    public String importStudents() {
        log.info("Loading import students page");
        return "admin/students/import";
    }
}