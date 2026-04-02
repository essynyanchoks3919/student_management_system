package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    
    Optional<Semester> findBySemesterCode(String semesterCode);
    
    @Query("SELECT s FROM Semester s WHERE s.isActive = true")
    Optional<Semester> findActiveSemester();
    
    @Query("SELECT s FROM Semester s WHERE s.semesterYear = :year ORDER BY s.semesterNumber ASC")
    List<Semester> findByYear(@Param("year") Integer year);
}