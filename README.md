# Project Description
This project is a Student Management System designed to manage student records, enrollment, and academic performance efficiently.

# System Requirements
- JDK 11 or higher  
- Maven 3.6 or higher  
- MySQL 5.7 or higher  
- MySQL Workbench 8.0 or higher  

# Project Setup
1. **Clone the repository:**  
   ```bash  
   git clone https://github.com/essynyanchoks3919/student_management_system.git  
   ```

2. **Navigate into the project directory:**  
   ```bash  
   cd student_management_system  
   ```

3. **Install dependencies:**  
   ```bash  
   mvn install  
   ```  

# MySQL Workbench Configuration
1. Open MySQL Workbench and click on `+` to create a new connection.  
2. **Connection Name:** university_database  
3. **Username:** root  
4. **Password:** 1111  
5. Connect to the database with the same parameters used to create a new connection.

# Running the Project
1. Ensure the MySQL service is running:  
   - Start the MySQL service via your command line or MySQL Workbench.  
2. **Build the project with Maven:**  
   ```bash  
   mvn clean package  
   ```  
3. **Run the application:**  
   ```bash  
   mvn spring-boot:run  
   ```  
4. Wait for a successful startup message in the console.

# Login Instructions
- Navigate to [http://localhost:8080/login](http://localhost:8080/login)  
- **Admin Username:** admin  
- **Admin Password:** admin123  
- Change the default credentials in the user management section once you log in.

# Key Features
**Core Functionalities**
**Student Profile Management**

**1** Create, read, update, and delete student records
Store comprehensive student information
Maintain academic history and credentials
Authentication & Authorization

**2** Secure login system with role-based access control
Spring Security integration with JWT token support
User session management and protection
Database Persistence

**3** MySQL database backend
JPA/Hibernate ORM for data mapping
Automatic schema generation and updates
User Interface

**4** Responsive HTML/CSS frontend
Thymeleaf template engine for server-side rendering
Intuitive navigation and user experience
Input Validation

**5** Server-side validation using Spring Validation
Data integrity checks
Error handling and user feedback

# System Architecture
- The project follows a layered architecture with separation of concerns between the presentation, service, and data access layers.

# Innovation Aspects
- Utilizes modern technologies such as Spring Boot, MySQL, and RESTful APIs for enhanced performance and scalability.

# Challenges Encountered
- Configuring MySQL with Spring Boot initially posed challenges due to version compatibility issues.

# Lessons Learned
- Gained experience in integrating MySQL with Spring Boot applications and managing dependencies effectively.

---  
*Current Date and Time: 2026-04-02 11:22:08 (UTC)*  
*Prepared by: essynyanchoks3919*
