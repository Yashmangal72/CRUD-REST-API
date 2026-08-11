# CRUD REST API

A Spring Boot based CRUD REST API for managing employee records.

This project was built as part of my Java Full Stack development journey to understand and implement REST API development using layered architecture, DTOs, request validation, exception handling, and HTTP status codes.

---

## 🚀 Features

- Create an employee
- Retrieve all employees
- Retrieve an employee by ID
- Update an employee
- Delete an employee
- DTO-based request and response handling
- Request validation using Jakarta Bean Validation
- Global exception handling
- Custom validation error responses
- Custom `EmployeeNotFoundException`
- Automatic employee ID generation
- Proper HTTP status codes
- RESTful API design
- Tested using Postman

---

## 🛠️ Technologies Used

- **Java**
- **Spring Boot**
- **Spring Web**
- **Maven**
- **Jakarta Bean Validation**
- **Postman**
- **Git & GitHub**

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
                    Client
                      │
                      ▼
              ┌───────────────┐
              │   Controller  │
              └───────┬───────┘
                      │
                EmployeeRequest
                      │
                      ▼
              ┌───────────────┐
              │    Service    │
              └───────┬───────┘
                      │
                   Employee
                      │
                      ▼
              ┌───────────────┐
              │  Repository   │
              └───────┬───────┘
                      │
                      ▼
                   HashMap
```

### Response Flow

```text
HashMap
   ↓
Employee
   ↓
Service
   ↓
EmployeeResponse
   ↓
Controller
   ↓
JSON Response
```

---

## 📁 Project Structure

```text
CRUD_REST_API
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── yash/
│   │   │           └── crud_rest_api/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   └── EmployeeService.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   └── Employee.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   ├── EmployeeRequest.java
│   │   │               │   └── EmployeeResponse.java
│   │   │               │
│   │   │               └── exception/
│   │   │                   ├── EmployeeNotFoundException.java
│   │   │                   ├── GlobalExceptionHandler.java
│   │   │                   └── ValidationErrorResponse.java
│   │   │
│   │   └── resources/
│   │
│   └── test/
│
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 🔗 REST API Endpoints

| HTTP Method | Endpoint | Description | Success Status |
|---|---|---|---|
| `POST` | `/employees` | Create a new employee | `201 Created` |
| `GET` | `/employees` | Retrieve all employees | `200 OK` |
| `GET` | `/employees/{id}` | Retrieve employee by ID | `200 OK` |
| `PUT` | `/employees/{id}` | Update an employee | `200 OK` |
| `DELETE` | `/employees/{id}` | Delete an employee | `204 No Content` |

---

# 📌 API Usage

## 1. Create Employee

### Request

```http
POST /employees
Content-Type: application/json
```

### Request Body

```json
{
  "name": "Yash",
  "department": "IT",
  "email": "yash@example.com",
  "salary": 50000
}
```

### Response

```json
{
  "id": 1,
  "name": "Yash",
  "department": "IT",
  "email": "yash@example.com",
  "salary": 50000.0
}
```

Response status:

```text
201 Created
```

---

## 2. Get All Employees

### Request

```http
GET /employees
```

### Response

```json
[
  {
    "id": 1,
    "name": "Yash",
    "department": "IT",
    "email": "yash@example.com",
    "salary": 50000.0
  },
  {
    "id": 2,
    "name": "Rahul",
    "department": "HR",
    "email": "rahul@example.com",
    "salary": 45000.0
  }
]
```

Response status:

```text
200 OK
```

---

## 3. Get Employee By ID

### Request

```http
GET /employees/1
```

### Response

```json
{
  "id": 1,
  "name": "Yash",
  "department": "IT",
  "email": "yash@example.com",
  "salary": 50000.0
}
```

Response status:

```text
200 OK
```

---

## 4. Update Employee

### Request

```http
PUT /employees/1
Content-Type: application/json
```

### Request Body

```json
{
  "name": "Yash",
  "department": "IT",
  "email": "yash@example.com",
  "salary": 60000
}
```

### Response

```json
{
  "id": 1,
  "name": "Yash",
  "department": "IT",
  "email": "yash@example.com",
  "salary": 60000.0
}
```

Response status:

```text
200 OK
```

---

## 5. Delete Employee

### Request

```http
DELETE /employees/1
```

Response status:

```text
204 No Content
```

The response body is empty because the employee has been successfully deleted.

---

# 🛡️ Validation

The API validates incoming employee data before processing the request.

### Validation Rules

| Field | Validation |
|---|---|
| `name` | Required, 2–50 characters |
| `department` | Required |
| `email` | Required and must be a valid email |
| `salary` | Must be greater than 0 |

### Invalid Request

```json
{
  "name": "",
  "department": "",
  "email": "wrong",
  "salary": -5000
}
```

### Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Name is required",
    "department": "Department is required",
    "email": "Invalid email",
    "salary": "Salary must be greater than 0"
  }
}
```

Response status:

```text
400 Bad Request
```

---

# ⚠️ Exception Handling

The project uses a global exception handler with `@RestControllerAdvice`.

If an employee doesn't exist:

```http
GET /employees/999
```

the API returns:

```text
404 Not Found
```

with an appropriate error message.

Example:

```text
Employee with ID 999 not found
```

This prevents controllers from having to handle the same exception logic repeatedly.

---

# 🔢 Automatic ID Generation

The current repository implementation uses an in-memory `HashMap` and an ID counter to generate employee IDs.

Example:

```text
First employee  → ID 1
Second employee → ID 2
Third employee  → ID 3
```

When updating an existing employee, the existing ID is preserved.

> Note: This project currently uses an in-memory `HashMap` for learning purposes. Data is not persisted after the application is restarted.

---

# ▶️ How to Run

## Prerequisites

Make sure you have:

- Java installed
- Maven installed (or use the included Maven Wrapper)
- IntelliJ IDEA or another Java IDE
- Postman or another API testing tool

---

## Clone the Repository

```bash
git clone https://github.com/Yashmangal72/CRUD-REST-API.git
```

Navigate into the project:

```bash
cd CRUD-REST-API
```

---

## Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or using the Maven Wrapper on Windows:

```bash
.\mvnw spring-boot:run
```

Alternatively, open the project in IntelliJ IDEA and run:

```text
CrudRestApiApplication
```

The application will start on:

```text
http://localhost:8080
```

Use Postman or another API client to test the endpoints.

---

# 🧪 Testing

The API was tested using **Postman**.

The following scenarios were tested:

```text
POST /employees
        ↓
201 Created

GET /employees
        ↓
200 OK

GET /employees/{id}
        ↓
200 OK

PUT /employees/{id}
        ↓
200 OK

DELETE /employees/{id}
        ↓
204 No Content

GET /employees/{invalid-id}
        ↓
404 Not Found

POST /employees with invalid data
        ↓
400 Bad Request
```

All CRUD operations and validation scenarios were successfully tested.

---

# 📚 Concepts Practiced

This project helped reinforce the following concepts:

- REST API design
- HTTP methods
- HTTP status codes
- Spring Boot
- Spring Web
- `@RestController`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`
- `@PathVariable`
- `@RequestBody`
- `ResponseEntity`
- Constructor Dependency Injection
- Service Layer
- Repository Layer
- DTO pattern
- Entity-to-DTO mapping
- Jakarta Bean Validation
- `@Valid`
- `@NotBlank`
- `@Email`
- `@Size`
- `@Positive`
- Custom exceptions
- `@ExceptionHandler`
- `@RestControllerAdvice`
- Global exception handling
- Maven
- Git
- GitHub
- Postman API testing

---

# 🔮 Future Improvements

The current project intentionally uses an in-memory `HashMap`.

Possible future improvements include:

- Replace `HashMap` with Spring Data JPA
- Add MySQL database
- Use `@Entity` and `@Id`
- Use `@GeneratedValue`
- Add Spring Data JPA repositories
- Add pagination and sorting
- Add search/filtering
- Add Spring Security
- Add JWT authentication
- Add unit and integration tests
- Add API documentation using Swagger/OpenAPI
- Dockerize the application

---

# 👨‍💻 Author

**Yash Mangal**

Java Full Stack Development Learner

GitHub:  
https://github.com/Yashmangal72

---

## ⭐ Project Status

**Completed ✅**

This project represents a completed learning milestone in my Java Full Stack development journey, focusing on building REST APIs with Spring Boot, layered architecture, DTOs, validation, exception handling, and API testing.
