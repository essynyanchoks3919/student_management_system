# Student Management System

## Overview
This project is designed to help manage student information effectively through a web-based interface.

## Running the Project
To get started with the Student Management System, follow the instructions below:

### Prerequisites
- Ensure you have [Node.js](https://nodejs.org/) installed on your machine.
- Clone the repository using the command:
  ```bash
  git clone https://github.com/essynyanchoks3919/student_management_system.git
  ```
- You will also need to install MySQL for the database management system.

### Setting Up MySQL Workbench
1. **Install MySQL Workbench**: Download it from the [official MySQL website](https://dev.mysql.com/downloads/workbench/).
2. **Create a New Connection**:
   - Open MySQL Workbench.
   - Click on the `+` sign to create a new connection.
   - Fill in the connection details:
     - Hostname: `localhost`
     - Port: `3306`
     - Username: `root`
     - Password: (leave empty if no password is set, or enter your MySQL root password)
3. **Create the Database**:
   - Use the following SQL command to create a new database for the project:
     ```sql
     CREATE DATABASE student_management;
     ```

### Configuration
- Update the database connection settings in the `config.js` (or corresponding file) to match your MySQL setup:
```javascript
module.exports = {
    host: 'localhost',
    user: 'root',
    password: '', // your password here if you set one
    database: 'student_management'
};
```

### Running the Application
1. Navigate to the project directory:
   ```bash
   cd student_management_system
   ```
2. Install the required dependencies:
   ```bash
   npm install
   ```
3. Start the application:
   ```bash
   npm start
   ```
4. Open your browser and go to `http://localhost:3000`.

### Admin Login Credentials
- **Username**: admin
- **Password**: admin123

Make sure to change the default admin credentials after the initial setup to secure your application.

## Conclusion
You are now ready to manage student information efficiently using this system! For further assistance, please refer to the documentation or contact support.