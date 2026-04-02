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

**1**. Create, read, update, and delete student records
Store comprehensive student information
Maintain academic history and credentials
Authentication & Authorization

**2**. Secure login system with role-based access control
Spring Security integration with JWT token support
User session management and protection
Database Persistence

**3**. MySQL database backend
JPA/Hibernate ORM for data mapping
Automatic schema generation and updates
User Interface

**4**. Responsive HTML/CSS frontend
Thymeleaf template engine for server-side rendering
Intuitive navigation and user experience
Input Validation

**5**. Server-side validation using Spring Validation
Data integrity checks
Error handling and user feedback

# System Architecture
The project follows a layered architecture with separation of concerns between the presentation, service, and data access layers.
**Technology Stack**  
- Backend:Spring Boot Framework
- Language:Java   	
- Build Tool:Maven	
- Database: MySQL	
- ORM: JPA
- Frontend Template:Thymeleaf

**Key Architectural Patterns**
- MVC Pattern: Separation of Model, View, and Controller
- Layered Architecture: Clear separation between presentation, business logic, and data access layers
- Repository Pattern: Data access abstraction using Spring Data JPA
- Service Layer: Business logic encapsulation
- Security Layer: Spring Security with JWT token-based authentication

# Technology Integration
**1** Spring Boot Starter Dependencies: Utilized for simplified configuration
**2** JPA Data Relationships: Proper entity mapping and relationships
**3** Spring Security: Role-based access control (RBAC)
**4** JWT Authentication: Stateless authentication mechanism
**5** Lombok: Boilerplate code reduction (getters, setters, constructors)

# Innovation & Unique Aspects
1. Automatic Database Initialization
The system automatically creates the university_sms database on first run
No manual database setup required
Significantly reduces setup friction for new developers
2. JWT Token-Based Authentication
Implements stateless authentication using JSON Web Tokens
Allows for scalability and integration with frontend frameworks
Secure token validation and user session management
Supports diverse client applications (web, mobile)
3. Development-Friendly Configuration
Disabled Thymeleaf caching for hot reload capability
DEBUG logging for comprehensive troubleshooting
Preconfigured application properties for XAMPP/local MySQL
Automatic schema updates without manual migrations
4. Java 21 Language Features
Utilizes modern Java 21 capabilities
Benefits from recent language improvements and performance optimizations
Future-proof architecture aligned with current Java standards
5. Comprehensive Dependency Stack
Integration of multiple Spring Boot starters for rapid development
Pre-configured security and validation frameworks
Built-in testing dependencies for quality assurance
Lombok integration for cleaner, more maintainable code
6. Thymeleaf Server-Side Rendering
Dynamic HTML generation with Spring Security integration
Role-based template rendering
Simplified backend-frontend integration without requiring separate REST APIs

 # Challenges Encountered
1. Database Connectivity & Configuration
Challenge: Configuring MySQL connection with proper timezone settings and SSL handling
Impact: Initial deployment issues with database connection timeouts
Resolution: Implemented createDatabaseIfNotExist flag and proper timezone configuration (serverTimezone=UTC)
2. Spring Boot Version Compatibility
Challenge: Working with Spring Boot 4.0.5 (relatively new version with potential compatibility issues)
Impact: Some dependencies required version alignment; documentation not comprehensive
Resolution: Careful dependency management and testing to ensure proper integration
3. Spring Security with JWT Integration
Challenge: Implementing secure JWT token generation and validation alongside Spring Security
Impact: Required understanding of authentication flow and token lifecycle
Resolution: Utilized JJWT library (0.11.5) with proper configuration and custom security filters
4. Thymeleaf & Spring Security Integration
Challenge: Properly integrating Thymeleaf template engine with Spring Security for role-based rendering
Impact: Had to add thymeleaf-extras-springsecurity6 dependency for security tag library
Resolution: Implemented proper security context propagation to templates
5. Development Environment Setup
Challenge: Ensuring consistency across different developer machines (Windows/Linux/Mac with XAMPP)
Impact: Variable setup times and environment-specific issues
Resolution: Created comprehensive setup documentation and automatic database creation
6. Lombok Annotation Processing
Challenge: Configuring Maven compiler plugin for Lombok annotation processing
Impact: Initial build failures due to missing annotation processor paths
Resolution: Added proper annotation processor configuration in Maven plugin
7. Hibernate DDL Management
Challenge: Balancing automatic schema generation during development with production-safe migrations
Impact: Risk of unintended data loss during development iterations
Resolution: Set DDL mode to update for development with clear documentation for production
8. Test Dependency Configuration
Challenge: Setting up multiple test dependencies and ensuring they don't conflict
Impact: Potential runtime conflicts and increased build complexity
Resolution: Proper scoping of test dependencies and maven exclusion rules

# Lessons Learned
1. Configuration Management is Critical
Lesson: Proper configuration setup can significantly reduce deployment issues
Application: Implemented centralized application.properties with clear documentation and multiple preset configurations
Future Impact: Will use configuration profiles (dev/test/prod) for environment-specific settings
2. Security Should Be Implemented Early
Lesson: Adding security features later is more complex than building them in from the start
Application: Integrated Spring Security and JWT from project initialization
Future Impact: Will follow security-first development practices in all projects
3. Dependency Management Requires Careful Planning
Lesson: Version conflicts and incompatibilities are easier to prevent than to resolve
Application: Used Spring Boot's dependency management to minimize version conflicts
Future Impact: Will use Bill of Materials (BOM) and version catalogs for complex projects
4. Automated Schema Management Improves Developer Experience
Lesson: Automatic database setup reduces friction and enables faster development cycles
Application: Utilized Hibernate's automatic schema generation with database auto-creation flag
Future Impact: Will prioritize developer experience in future projects
5. Logging is Essential for Debugging
Lesson: Proper logging configuration saves significant debugging time
Application: Configured DEBUG level logging for Spring Web and Security components
Future Impact: Will implement structured logging and log aggregation strategies
6. Documentation Eases Knowledge Transfer
Lesson: Clear setup and architecture documentation is invaluable for team collaboration
Application: Created comprehensive setup instructions and inline code documentation
Future Impact: Will maintain living documentation as the project evolves
7. Role-Based Access Control is Complex but Essential
Lesson: Implementing RBAC properly requires careful planning and testing
Application: Integrated Spring Security's role-based access control with JWT tokens
Future Impact: Will use role hierarchies and permission-based access for finer-grained control
8. Testing Framework Integration Early
Lesson: Setting up testing infrastructure early in development prevents rework
Application: Included Spring Test, Security Test, and other testing dependencies
Future Impact: Will follow TDD (Test-Driven Development) practices
9. Front-End and Back-End Coordination
Lesson: Decisions about authentication (JWT vs. session) affect the entire architecture
Application: Chose JWT for stateless, scalable authentication
Future Impact: Will plan integration strategy before implementation
10. Performance Optimization Starts with Good Defaults
Lesson: Using connection pooling and proper timeout settings prevents scalability issues
Application: Configured HikariCP through commented-out properties for future use
Future Impact: Will implement database connection pooling configuration as soon as performance testing begins
---  
*Current Date and Time: 2026-04-02 11:22:08 (UTC)*  
*Prepared by: essynyanchoks3919*
