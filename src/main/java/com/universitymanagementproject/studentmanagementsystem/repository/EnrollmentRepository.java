package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    @Query("SELECT e FROM Enrollment e WHERE e.student.studentId = :studentId")
    List<Enrollment> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.courseOffering.courseOfferingId = :courseOfferingId")
    List<Enrollment> findByCourseOfferingId(@Param("courseOfferingId") Long courseOfferingId);
    
    @Query("SELECT e FROM Enrollment e WHERE e.status = :status")
    List<Enrollment> findByStatus(@Param("status") Enrollment.EnrollmentStatus status);
    
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.courseOffering.courseOfferingId = :courseOfferingId")
    Long countByCourseOfferingId(@Param("courseOfferingId") Long courseOfferingId);
}