# 📝 Blog Management System

A complete Blog Management REST API built using **Spring Boot**. The project demonstrates modern backend development practices including JWT Authentication, Spring Security, CRUD Operations, Role-Based Authorization, File Uploads, Swagger Documentation, Pagination, Search, Unit Testing, and Integration Testing.

---

# 🚀 Features

## Authentication & Security

- User Registration
- User Login
- JWT Authentication
- Spring Security
- Password Encryption (BCrypt)
- Role-Based Authorization (ADMIN & USER)

---

## User Management

- Create User
- Get All Users
- Get User By ID
- Get User By Email
- Update User
- Delete User
- Upload Profile Picture

---

## Post Management

- Create Post
- Update Post
- Delete Post
- Search Posts
- Native Search
- Pagination
- Sorting
- Recent Posts

---

## Comment Management

- Add Comment
- View Comments
- Delete Comment

---

## Like System

- Like a Post
- Unlike a Post
- Count Likes

---

## Additional Features

- Swagger UI
- Global Exception Handling
- DTO Mapping
- Validation
- File Upload
- Local File Storage
- Unit Testing (JUnit & Mockito)
- Integration Testing (MockMvc)

---

# 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT
- Maven
- Swagger (OpenAPI)
- JUnit 5
- Mockito

---

# 📁 Project Structure

```
src
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
└── UsermanagementApplication.java
```

---

# 🗄 Database Design

```
User
 │
 ├── One To Many
 ▼
Post
 │
 ├── One To Many
 ▼
Comment

User
 │
 ├── One To Many
 ▼
Like

Post
 │
 ├── One To Many
 ▼
Like
```

---

# 🔑 Authentication Flow

```
Register
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

# 📚 API Endpoints

## Authentication

| Method | Endpoint |
|----------|----------------------|
| POST | /api/auth/register |
| POST | /api/auth/login |

---

## Users

| Method | Endpoint |
|----------|-------------------------|
| GET | /api/users |
| GET | /api/users/{id} |
| GET | /api/users/email/{email} |
| POST | /api/users |
| PUT | /api/users/{id} |
| DELETE | /api/users/{id} |
| POST | /api/users/{id}/upload |

---

## Posts

| Method | Endpoint |
|----------|---------------------------|
| GET | /api/posts |
| GET | /api/posts/{id} |
| POST | /api/posts |
| POST | /api/posts/user/{userId} |
| PUT | /api/posts/{id} |
| DELETE | /api/posts/{id} |
| GET | /api/posts/search |
| GET | /api/posts/search-native |
| GET | /api/posts/recent |

---

## Comments

| Method | Endpoint |
|----------|----------------|
| GET | /api/comments |
| POST | /api/comments |
| DELETE | /api/comments/{id} |

---

## Likes

| Method | Endpoint |
|----------|----------------------------|
| POST | /api/likes/post/{postId}/user/{userId} |
| DELETE | /api/likes/{id} |
| GET | /api/likes/post/{postId} |

---

# ⚙️ Installation

### Clone Repository

```bash
git clone <repository-url>
```

### Create PostgreSQL Database

```
blog_db
```

### Configure

Update `application.properties` with your PostgreSQL credentials.

### Run

```bash
./mvnw spring-boot:run
```

or simply run

```
UsermanagementApplication.java
```

---

# 📖 Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# ✅ Project Highlights

- RESTful APIs
- Layered Architecture
- DTO Mapping
- Bean Validation
- Global Exception Handling
- JWT Authentication
- Role-Based Authorization
- Pagination
- Sorting
- Searching
- File Upload
- Swagger Documentation
- Unit Testing
- Integration Testing

---

# 👨‍💻 Developed By

**Yash Gupta**

B.Tech Computer Science & Engineering

