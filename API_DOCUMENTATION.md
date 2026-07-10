# API Documentation

Base URL

```
http://localhost:8080
```

---

# Authentication APIs

## Register User

**POST**

```
/api/auth/register
```

### Authentication Required

No

### Request

```json
{
  "username":"Yash",
  "email":"yash@gmail.com",
  "password":"password123"
}
```

### Success Response

```json
{
  "id":1,
  "username":"Yash",
  "email":"yash@gmail.com"
}
```

Status Code

```
200 OK
```

---

## Login

**POST**

```
/api/auth/login
```

### Authentication Required

No

### Request

```json
{
  "email":"yash@gmail.com",
  "password":"password123"
}
```

### Success Response

```json
{
  "token":"<JWT_TOKEN>"
}
```

Status Code

```
200 OK
```

---

# User APIs

## Get All Users

**GET**

```
/api/users
```

Authentication

Bearer Token (ADMIN)

Response

```
List<UserDTO>
```

---

## Get User By ID

**GET**

```
/api/users/{id}
```

Authentication

Bearer Token

---

## Get User By Email

**GET**

```
/api/users/email/{email}
```

Authentication

Bearer Token

---

## Create User

**POST**

```
/api/users
```

Authentication

Bearer Token

---

## Update User

**PUT**

```
/api/users/{id}
```

Authentication

Bearer Token

### Request

```json
{
  "username":"Updated User",
  "email":"updated@gmail.com"
}
```

---

## Delete User

**DELETE**

```
/api/users/{id}
```

Authentication

Bearer Token (ADMIN)

---

## Upload Profile Picture

**POST**

```
/api/users/{id}/upload
```

Authentication

Bearer Token

Body

```
form-data
```

| Key | Type |
|------|------|
| file | File |

Response

```json
{
   "message":"Profile picture uploaded successfully",
   "fileName":"uuid.jpg"
}
```

---

# Post APIs

## Get All Posts

**GET**

```
/api/posts
```

Authentication

No

Supports

- Pagination
- Sorting

Example

```
/api/posts?page=0&size=5&sortBy=id&direction=asc
```

---

## Get Post By ID

```
GET /api/posts/{id}
```

---

## Create Post

```
POST /api/posts
```

---

## Create Post For User

```
POST /api/posts/user/{userId}
```

---

## Update Post

```
PUT /api/posts/{id}
```

---

## Delete Post

```
DELETE /api/posts/{id}
```

---

## Search Posts

```
GET /api/posts/search?keyword=spring
```

---

## Native Search

```
GET /api/posts/search-native?keyword=spring
```

---

## Recent Posts

```
GET /api/posts/recent
```

---

# Comment APIs

## Get Comments

```
GET /api/comments
```

---

## Add Comment

```
POST /api/comments
```

---

## Delete Comment

```
DELETE /api/comments/{id}
```

---

# Like APIs

## Like Post

```
POST /api/likes/post/{postId}/user/{userId}
```

---

## Unlike

```
DELETE /api/likes/{id}
```

---

## Count Likes

```
GET /api/likes/post/{postId}
```

---

# Common HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Success |
| 201 | Resource Created |
| 400 | Validation Error |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Resource Not Found |
| 409 | Duplicate Resource |
| 500 | Internal Server Error |

---

# Authorization

Protected APIs require a JWT Bearer Token.

Example Header

```
Authorization: Bearer <JWT_TOKEN>
```

Generate the token using:

```
POST /api/auth/login
```