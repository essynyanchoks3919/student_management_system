package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByEmail(String email);
    
    List<Student> findByFirstNameContaining(String firstName);
    
    List<Student> findByLastNameContaining(String lastName);
    
    List<Student> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
    
    List<Student> findByStatus(Student.StudentStatus status);
    
    @Query("SELECT s FROM Student s WHERE s.department.departmentId = :departmentId")
    List<Student> findByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT s FROM Student s WHERE s.cgpa >= :minCgpa ORDER BY s.cgpa DESC")
    List<Student> findTopStudentsByCgpa(@Param("minCgpa") Double minCgpa);
    
    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = :status")
    Long countByStatus(@Param("status") Student.StudentStatus status);
}