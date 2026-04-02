package com.universitymanagementproject.studentmanagementsystem.repository;

import com.universitymanagementproject.studentmanagementsystem.entity.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    
    @Query("SELECT co FROM CourseOffering co WHERE co.semester.semesterId = :semesterId")
    List<CourseOffering> findBySemesterId(@Param("semesterId") Long semesterId);
    
    @Query("SELECT co FROM CourseOffering co WHERE co.faculty.facultyId = :facultyId")
    List<CourseOffering> findByFacultyId(@Param("facultyId") Long facultyId);
    
    @Query("SELECT co FROM CourseOffering co WHERE co.course.courseId = :courseId")
    List<CourseOffering> findByCourseId(@Param("courseId") Long courseId);
    
    @Query("SELECT co FROM CourseOffering co WHERE co.semester.isActive = true")
    List<CourseOffering> findCurrentSemesterOfferings();
    
    @Query("SELECT co FROM CourseOffering co WHERE co.enrolledCount < co.course.capacity")
    List<CourseOffering> findAvailableCourseOfferings();
}