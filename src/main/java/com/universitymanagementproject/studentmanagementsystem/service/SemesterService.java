package com.universitymanagementproject.studentmanagementsystem.service;

import com.universitymanagementproject.studentmanagementsystem.entity.Semester;
import com.universitymanagementproject.studentmanagementsystem.repository.SemesterRepository;
import com.universitymanagementproject.studentmanagementsystem.exception.ResourceNotFoundException;
import com.universitymanagementproject.studentmanagementsystem.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects; // 1. Added Import

@Service
@Slf4j
@Transactional
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public Semester createSemester(Semester semester) {
        // Guard against null input
        Objects.requireNonNull(semester, "Semester object cannot be null");
        log.info("Creating semester: {}", semester.getSemesterCode());
        
        if (semesterRepository.findBySemesterCode(semester.getSemesterCode()).isPresent()) {
            throw new ValidationException("Semester code already exists: " + semester.getSemesterCode());
        }
    }

    public Semester updateSemester(Long id, Semester semesterDetails) {
        Objects.requireNonNull(id, "Semester ID cannot be null");
        Objects.requireNonNull(semesterDetails, "Semester details cannot be null");
        
        log.info("Updating semester: {}", id);
        
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));

        String oldValues = semester.toString();
        
        // Use requireNonNull if these fields are MANDATORY for an update
        semester.setSemesterName(Objects.requireNonNull(semesterDetails.getSemesterName(), "Semester name is required"));
        semester.setIsActive(Objects.requireNonNull(semesterDetails.getIsActive(), "Active status is required"));
        semester.setSemesterYear(Objects.requireNonNull(semesterDetails.getSemesterYear(), "Semester year is required"));

    }

    public void deleteSemester(Long id) {
        Objects.requireNonNull(id, "ID to delete cannot be null");
        log.info("Deleting semester: {}", id);
        
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));

    }

    public Semester getSemesterById(Long id) {
        Objects.requireNonNull(id, "Search ID cannot be null");
        log.info("Fetching semester: {}", id);
        return semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));
    }

    public Page<Semester> getAllSemesters(Pageable pageable) {
        Objects.requireNonNull(pageable, "Pageable parameters cannot be null");
        log.info("Fetching all semesters");
        return semesterRepository.findAll(pageable);
    }

    public Semester getActiveSemester() {
        log.info("Fetching active semester");
        return semesterRepository.findActiveSemester()
                .orElseThrow(() -> new ResourceNotFoundException("No active semester found"));
    }

    public List<Semester> getSemestersByYear(Integer year) {
        Objects.requireNonNull(year, "Year cannot be null");
        log.info("Fetching semesters for year: {}", year);
        return semesterRepository.findByYear(year);
    }

    public Semester getSemesterByCode(String semesterCode) {
        Objects.requireNonNull(semesterCode, "Semester code cannot be null");
        log.info("Fetching semester by code: {}", semesterCode);
        return semesterRepository.findBySemesterCode(semesterCode)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with code: " + semesterCode));
    }
}
