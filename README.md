# Staff Management System (Java | Swing | SQLite)

## 📌 Project Overview

The Staff Management System is a desktop application developed in **Java** using **Object-Oriented Programming (OOP)** principles.  
It provides a graphical interface for managing employee records, including adding, editing, deleting, searching, and filtering employees.

The system uses **Swing for GUI** and **SQLite for database storage**, ensuring that all employee data is permanently saved even after the application is closed.

---

## 🎯 Features

- Add new employees
- Search employee by ID
- Edit employee details
- Delete employee records
- Display all employees in a table
- Filter employees with salary greater than 20,000
- Input validation for all fields
- Persistent data storage using SQLite database

---

## 🛠 Technologies Used

- Java (JDK 8+)
- Swing (GUI)
- SQLite (Database)
- JDBC (Database connectivity)

---
## 📁 Project Structure

com.mycompany.staffmanagementsystem

│
├── StaffManagementSystemApp.java # Main entry point
├── Employee.java # Employee model class
├── EmployeeValidator.java # Input validation rules
├── SalaryCalculator.java # Salary logic handling
├── DatabaseHandler.java # SQLite database operations
├── StaffManagementSystem.java # Core business logic
└── StaffGUI.java # Graphical User Interface
---
## 🗄 Database Schema

### Table: `employees`

| Column  | Type             | Description        |
|---------|-----------------|--------------------|
| id      | INTEGER PRIMARY KEY | Unique employee ID |
| name    | TEXT            | Employee name      |
| age     | INTEGER         | Employee age       |
| address | TEXT            | Employee address   |
| role    | TEXT            | Employee role      |
| salary  | REAL            | Employee salary    |

---

## ⚙ System Workflow

1. Application starts
2. SQLite database is initialized automatically
3. GUI window is displayed
4. User selects an operation:
   - Insert employee
   - Search employee
   - Edit employee
   - Delete employee
   - View all employees
   - Filter salary > 20,000
5. Input is validated before processing
6. Data is stored/updated in the database
7. Table is refreshed in GUI

---

## ✔ Validation Rules

- ID must be greater than 0
- Name cannot be empty
- Age must be 18 or older
- Address cannot be empty
- Role cannot be empty
- Salary must be greater than 0

---

## 💾 Data Persistence

All employee data is stored in a local SQLite database file:
staff_management.db

This ensures that all records remain saved even after closing the application.

---

## 🖥 GUI Overview

The interface includes:

### Input Fields:
- ID
- Name
- Age
- Address
- Role
- Salary

### Buttons:
- Insert
- Search
- Edit
- Delete
- Show All
- Salary > 20k
- Clear

### Table:
Displays all employee records in a structured format.

---

## ▶ How to Run

### 1. Requirements
- Java JDK 8 or higher
- IDE (NetBeans / IntelliJ / Eclipse)
- SQLite JDBC driver

---

### 2. Add SQLite Dependency

If using Maven:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.3.0</version>
</dependency>
