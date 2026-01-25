# Library Management System (JDBC)

A console-based **Library Management System** developed using **Java**, **JDBC**, and **MySQL**.  
This project follows a **layered architecture (DTO–DAO–Service–UI)** and supports complete **CRUD operations** on library book records.

---

## Features

- Add new books
- Fetch book details by ID
- View all books
- Update book information
- Delete book records
- Input validation through service layer

---

## Technologies Used

- Java
- JDBC
- MySQL
- SQL

---

## Project Architecture

This project follows a **layered architecture** to ensure separation of concerns and easy maintenance.

- **DTO (Data Transfer Object)**  
  Transfers data between layers

- **DAO (Data Access Object)**  
  Handles all database operations using JDBC

- **Service Layer**  
  Contains business logic and validations

- **Main (UI Layer)**  
  Menu-driven console interface for user interaction

---

## Database Design

### Table: `books`

```sql
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    quantity INT
);

