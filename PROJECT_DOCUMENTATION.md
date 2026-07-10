# Blog Management System - Project Documentation

## 1. Project Overview

The Blog Management System is a RESTful backend application developed using Spring Boot. The project demonstrates secure API development with JWT Authentication, Role-Based Authorization, CRUD operations, validation, exception handling, file upload, pagination, searching, Swagger documentation, and testing.

---

# 2. Objectives

- Build a secure REST API.
- Implement JWT-based authentication.
- Implement role-based authorization.
- Learn Spring Boot architecture.
- Work with PostgreSQL using Spring Data JPA.
- Apply validation and exception handling.
- Perform unit and integration testing.
- Document APIs using Swagger.

---

# 3. Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-Based Authentication |
| Spring Data JPA | ORM |
| Hibernate | ORM Implementation |
| PostgreSQL | Database |
| Maven | Build Tool |
| Swagger/OpenAPI | API Documentation |
| JUnit 5 | Unit Testing |
| Mockito | Mocking Framework |
| MockMvc | Integration Testing |

---

# 4. Project Architecture

The project follows a layered architecture.

```
Client
        │
        ▼
Controllers
        │
        ▼
Services
        │
        ▼
Repositories
        │
        ▼
PostgreSQL Database
```

Each layer has a specific responsibility.

- Controller → Handles HTTP requests and responses.
- Service → Contains business logic.
- Repository → Interacts with the database.
- Entity → Represents database tables.
- DTO → Transfers data between layers.
- Mapper → Converts Entity ↔ DTO.

---

# 5. Package Structure

```
config
controller
dto
entity
enums
exception
mapper
repository
security
service
```

### config

Contains Spring Security and Swagger configuration.

### controller

Defines REST API endpoints.

### dto

Contains request and response objects.

### entity

Contains JPA entities.

### enums

Contains application enums such as Role.

### exception

Contains custom exceptions and global exception handling.

### mapper

Converts Entity objects into DTOs.

### repository

Handles database operations.

### security

Contains JWT Authentication Filter, JWT Utility, Security Configuration, and User Details Service.

### service

Contains application business logic.

---

# 6. Database Design

Tables

- users
- posts
- comments
- likes

Relationships

```
User
 ├── Posts
 ├── Comments
 └── Likes

Post
 ├── Comments
 └── Likes
```

---

# 7. Authentication Flow

```
User Registration
        │
        ▼
Login
        │
        ▼
JWT Token Generated
        │
        ▼
Bearer Token
        │
        ▼
Access Protected APIs
```

---

# 8. Authorization

Two roles are implemented.

- ROLE_ADMIN
- ROLE_USER

Example:

ADMIN

- Get all users
- Delete users

USER

- Login
- Create posts
- Like posts
- Comment on posts

---

# 9. Features

## Authentication

- Register
- Login
- JWT Authentication

## User Module

- CRUD Operations
- Profile Picture Upload

## Post Module

- CRUD Operations
- Pagination
- Sorting
- Searching

## Comment Module

- Add Comment
- Delete Comment

## Like Module

- Like
- Unlike
- Count Likes

## File Upload

- Multipart File Upload
- Local Storage

---

# 10. Validation

Implemented using Jakarta Validation.

Examples

- @NotBlank
- @Email
- @Size

---

# 11. Exception Handling

Implemented using

- ResourceNotFoundException
- DuplicateResourceException
- GlobalExceptionHandler

---

# 12. Testing

### Unit Testing

- JUnit 5
- Mockito

Service layer tested.

### Integration Testing

- SpringBootTest
- MockMvc

Controller endpoints tested.

---

# 13. API Documentation

Swagger/OpenAPI has been integrated.

Access using

```
http://localhost:8080/swagger-ui/index.html
```

---

# 14. Challenges Faced

- Configuring Spring Security.
- Implementing JWT Authentication.
- Handling role-based authorization.
- Writing unit tests using Mockito.
- Writing integration tests using MockMvc.
- Implementing multipart file upload.
- Managing Git branches and resolving conflicts.

---

# 15. Future Enhancements

- Refresh Token Authentication
- Docker Deployment
- Cloud Storage for Images
- Email Verification
- Password Reset
- CI/CD Pipeline
- AWS Deployment

---

# 16. Conclusion

This project demonstrates the implementation of a secure, scalable, and maintainable RESTful backend using Spring Boot following industry best practices such as layered architecture, JWT Authentication, DTO mapping, validation, exception handling, testing, and API documentation.