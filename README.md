# Employee Management System REST API

A fresher-friendly Spring Boot backend project that demonstrates REST API development, layered architecture, Spring Data JPA, Hibernate, MySQL, Bean Validation, exception handling, Swagger/OpenAPI and Postman testing.

## Tech Stack

- Java 11
- Spring Boot 2.7.18
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation
- Swagger/OpenAPI
- Postman

## Package Structure

```text
com.ems
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── service.impl
```

## Database Setup

Create the MySQL database before running the application:

```sql
CREATE DATABASE IF NOT EXISTS ems_db;
```

The same SQL is available in `mysql-schema.sql`.

Default database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ems_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Update the username or password if your local MySQL setup is different.

## Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The API runs at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/api/employees/search?department=IT` | Search employees by department |

## Sample Requests and Responses

### Create Employee

Request:

```json
{
  "firstName": "Amit",
  "lastName": "Gupta",
  "email": "amit.gupta@example.com",
  "department": "IT",
  "salary": 65000
}
```

Response:

```json
{
  "id": 1,
  "firstName": "Amit",
  "lastName": "Gupta",
  "email": "amit.gupta@example.com",
  "department": "IT",
  "salary": 65000,
  "createdAt": "2026-06-15T10:30:00"
}
```

### Validation Error

Request:

```json
{
  "firstName": "",
  "lastName": "Gupta",
  "email": "invalid-email",
  "department": "IT",
  "salary": -100
}
```

Response:

```json
{
  "timestamp": "2026-06-15T10:31:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/employees",
  "validationErrors": {
    "firstName": "First name is required",
    "email": "Email must be valid",
    "salary": "Salary must be greater than zero"
  }
}
```

### Employee Not Found

Response:

```json
{
  "timestamp": "2026-06-15T10:32:00",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id: 99",
  "path": "/api/employees/99",
  "validationErrors": null
}
```

## Postman Testing

Import this file into Postman:

```text
postman/Employee-Management-System.postman_collection.json
```

The collection includes:

- Create Employee
- Get All Employees
- Get Employee By ID
- Update Employee
- Delete Employee
- Search Employee By Department

Set the `employeeId` collection variable after creating an employee if the generated ID is not `1`.

## Project Highlights

- Clean layered architecture
- DTO pattern so entities are not exposed directly
- Constructor injection
- Bean Validation with meaningful messages
- Centralized exception handling using `@RestControllerAdvice`
- Swagger documentation
- MySQL table auto-creation with JPA/Hibernate
