# Room Management System - Detailed Documentation
## Overview
The Room Management System is a Java application designed to efficiently manage spaces such as classrooms in educational institutions. Built with Java's Swing library for graphical user interface, this system offers an interactive and user-friendly solution for reserving and allocating spaces, optimizing the use of available resources.



## Key Features
### Space Registration (Rooms and Auditoriums):
Allows adding spaces with details such as capacity, name, and location.

### Reservation Requests:
Provides the functionality to make fixed or occasional reservations for registered spaces.

### Report Generation:
Ability to generate detailed reports by room or course, providing insights into space utilization.

### Swing-Developed Graphical Interface:
An intuitive and responsive user interface, facilitating interaction with the system.

### System Requirements
- Java Development Kit (JDK) 8 or newer version.
- Java-compatible environment, such as Windows, MacOS, or Linux.
## Installation Guide
1. Project Download:
- Clone the Git repository or download the project in ZIP format.
2. Configuration:
- Extract the files (if necessary) and navigate to the project's root folder.
3. Execution:
- Open a terminal or command prompt in the project folder.
- Compile and execute the MenuInterface.java file using the command javac MenuInterface.java followed by java MenuInterface.
## User Manual
- Starting the Application: Execute MenuInterface.java to open the graphical interface.
- Space Registration: Use the registration options to add new rooms or auditoriums, providing the necessary data.
- Making a Request: Select the type of request (fixed or occasional) and fill in the relevant information.
Reports: Generate specific reports by room or course to obtain details of the reservations made.
## Architecture and Design
- Model-View-Controller (MVC): The application follows the MVC pattern, separating business logic from user interface.
- models/: Contains classes representing the system entities (Room, Auditorium, Request).
view/: Implements the graphical user interface with Swing.
- controller/: Manages business logic, interaction between the user interface and models.
## Contribution and Development
1. Contributing:
- Fork the project.
- Create a feature branch (git checkout -b feature/NewFeature).
- Commit your changes (git commit -am 'Add NewFeature').
- Push to the branch (git push origin feature/NewFeature).
- Open a Pull Request.