CREATE DATABASE IF NOT EXISTS university_sms;
USE university_sms;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    password_changed_date TIMESTAMP NULL,
    two_factor_enabled BOOLEAN DEFAULT FALSE
);

-- Departments Table
CREATE TABLE IF NOT EXISTS departments (
    department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_code VARCHAR(20) NOT NULL UNIQUE,
    department_name VARCHAR(200) NOT NULL,
    description TEXT,
    building VARCHAR(100),
    office_number VARCHAR(50)
);

-- Students Table
CREATE TABLE IF NOT EXISTS students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    address TEXT,
    enrollment_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    department_id BIGINT,
    cgpa DECIMAL(5,2) DEFAULT 0.0,
    total_credits_completed INT DEFAULT 0,
    total_credits_enrolled INT DEFAULT 0,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Faculty Table
CREATE TABLE IF NOT EXISTS faculty (
    faculty_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    hire_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    department_id BIGINT,
    specialization VARCHAR(255),
    qualification VARCHAR(255),
    office VARCHAR(100),
    office_hours VARCHAR(255),
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Courses Table
CREATE TABLE IF NOT EXISTS courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) NOT NULL UNIQUE,
    course_name VARCHAR(200) NOT NULL,
    description TEXT,
    credits INT NOT NULL,
    capacity INT NOT NULL,
    current_enrollment INT DEFAULT 0,
    department_id BIGINT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Semesters Table
CREATE TABLE IF NOT EXISTS semesters (
    semester_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    semester_code VARCHAR(20) NOT NULL UNIQUE,
    semester_name VARCHAR(200) NOT NULL,
    semester_year INT,
    semester_number INT,
    is_active BOOLEAN DEFAULT FALSE
);

-- Course Offerings Table
CREATE TABLE IF NOT EXISTS course_offerings (
    course_offering_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT NOT NULL,
    semester_id BIGINT NOT NULL,
    faculty_id BIGINT NOT NULL,
    enrolled_count INT DEFAULT 0,
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (semester_id) REFERENCES semesters(semester_id),
    FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id)
);

-- Enrollments Table
CREATE TABLE IF NOT EXISTS enrollments (
    enrollment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_offering_id BIGINT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    attendance_percentage INT DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_offering_id) REFERENCES course_offerings(course_offering_id)
);

-- Grades Table
CREATE TABLE IF NOT EXISTS grades (
    grade_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    enrollment_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    exam_score DECIMAL(5,2) DEFAULT 0.0,
    assignment_score DECIMAL(5,2) DEFAULT 0.0,
    project_score DECIMAL(5,2) DEFAULT 0.0,
    participation_score DECIMAL(5,2) DEFAULT 0.0,
    final_grade DECIMAL(5,2) DEFAULT 0.0,
    letter_grade VARCHAR(2),
    grade_point DECIMAL(3,2) DEFAULT 0.0,
    semester_id BIGINT,
    remarks TEXT,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
);

-- Fees Table
CREATE TABLE IF NOT EXISTS fees (
    fee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    status VARCHAR(50) NOT NULL,
    fine_amount DECIMAL(10,2) DEFAULT 0.0,
    description TEXT,
    invoice_number VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Rooms Table
CREATE TABLE IF NOT EXISTS rooms (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(50) NOT NULL UNIQUE,
    capacity INT NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    building VARCHAR(100),
    floor INT,
    has_projector BOOLEAN DEFAULT FALSE,
    has_ac BOOLEAN DEFAULT FALSE
);

-- Timetables Table
CREATE TABLE IF NOT EXISTS timetables (
    time_table_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_offering_id BIGINT NOT NULL,
    faculty_id BIGINT NOT NULL,
    room_id BIGINT,
    day_of_week VARCHAR(20),
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    semester_id BIGINT,
    FOREIGN KEY (course_offering_id) REFERENCES course_offerings(course_offering_id),
    FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id),
    FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
);

-- Audit Logs Table
CREATE TABLE IF NOT EXISTS audit_logs (
    audit_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    entity_type VARCHAR(100) NOT NULL,
    entity_id BIGINT NOT NULL,
    action VARCHAR(50) NOT NULL,
    old_value TEXT,
    new_value TEXT,
    modified_by VARCHAR(100) NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    ip_address VARCHAR(50)
);

-- Indexes for performance
CREATE INDEX idx_student_email ON students(email);
CREATE INDEX idx_student_department ON students(department_id);
CREATE INDEX idx_faculty_email ON faculty(email);
CREATE INDEX idx_course_code ON courses(course_code);
CREATE INDEX idx_enrollment_student ON enrollments(student_id);
CREATE INDEX idx_enrollment_course_offering ON enrollments(course_offering_id);
CREATE INDEX idx_grade_student ON grades(student_id);
CREATE INDEX idx_fee_student ON fees(student_id);
CREATE INDEX idx_audit_entity ON audit_logs(entity_type, entity_id);