# Student Management System

## MySQL Workbench Setup
1. **Download and Install MySQL Workbench**: Visit the official [MySQL website](https://dev.mysql.com/downloads/workbench/) and download the latest version suitable for your operating system. Follow the installation instructions provided.

2. **Open MySQL Workbench**: Launch the application after a successful installation.

3. **Create a New Connection**: Click on the `+` sign next to `MySQL Connections` to set up a new connection.
   - **Connection Name**: Enter a name for your connection (e.g., `StudentManagementSystem`).
   - **Hostname**: Typically `localhost` for local development.
   - **Port**: Default is `3306`.
   - **Username**: Enter your MySQL username.
   - Test the connection and save.

## Database Connection Details
- **Host**: `localhost`
- **Port**: `3306`
- **Database Name**: `student_management`
- **Username**: `your_mysql_username`
- **Password**: `your_mysql_password`

Ensure you've set up the database schema as described in the application documentation.

## Application Startup
1. **Clone the Repository**: Use the following command to clone:
   ```bash
   git clone https://github.com/essynyanchoks3919/student_management_system.git
   ```

2. **Install Dependencies**: Navigate to the project directory and run:
   ```bash
   npm install
   ```

3. **Start the Application**: Run the application using:
   ```bash
   npm start
   ```

4. **Access the Application**: Open your browser and navigate to `http://localhost:3000`.

## Login Instructions
- **Navigate to the Login Page**: Once the application is running, go to the login page.
- **Enter Credentials**: Use the following credentials to log in:
   - **Username**: `admin`
   - **Password**: `password`

Make sure to change the default credentials after your first login for security reasons.