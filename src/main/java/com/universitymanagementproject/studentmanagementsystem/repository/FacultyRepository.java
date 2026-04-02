package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    
    Optional<Faculty> findByEmail(String email);
    
    List<Faculty> findByFirstNameContaining(String firstName);
    
    List<Faculty> findByStatus(Faculty.FacultyStatus status);
    
    @Query("SELECT f FROM Faculty f WHERE f.department.departmentId = :departmentId")
    List<Faculty> findByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT f FROM Faculty f WHERE f.specialization = :specialization")
    List<Faculty> findBySpecialization(@Param("specialization") String specialization);
    
    @Query("SELECT COUNT(f) FROM Faculty f WHERE f.status = :status")
    Long countByStatus(@Param("status") Faculty.FacultyStatus status);
}