package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    Optional<Course> findByCourseCode(String courseCode);
    
    List<Course> findByCourseName(String courseName);
    
    List<Course> findByCourseNameContaining(String courseName);
    
    @Query("SELECT c FROM Course c WHERE c.department.departmentId = :departmentId")
    List<Course> findByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT c FROM Course c WHERE c.credits = :credits")
    List<Course> findByCredits(@Param("credits") Integer credits);
    
    @Query("SELECT c FROM Course c WHERE c.currentEnrollment < c.capacity")
    List<Course> findAvailableCourses();
}