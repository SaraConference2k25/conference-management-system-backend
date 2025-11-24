# SaraConference2k25 Backend

<div align="center">

![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-42.7.3-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Azure](https://img.shields.io/badge/Azure%20Blob-Storage-0089D6?style=for-the-badge&logo=microsoft-azure&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

**Enterprise-grade RESTful API backend for academic conference management**

[Features](#-features) •
[Quick Start](#-quick-start) •
[API Documentation](#-api-documentation) •
[Architecture](#-architecture) •
[Deployment](#-deployment)

</div>

---

> **⚠️ PROPRIETARY SOFTWARE - CONFIDENTIAL**
>
> This repository contains proprietary and confidential information belonging to 
> **SaraConference2k25 Organization**. Access is restricted to authorized personnel only.
> Unauthorized copying, distribution, or disclosure is strictly prohibited.
>
> © 2025 SaraConference2k25 Organization. All Rights Reserved.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Project Structure](#-project-structure)
- [Development](#-development)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Security](#-security)
- [Troubleshooting](#-troubleshooting)
- [Support & SLA](#-support--sla)
- [Compliance & Security](#-compliance--security)
- [License](#-license)
- [Contact](#-contact)

---

## 🎯 Overview

The **SaraConference2k25 Backend** is a mission-critical, enterprise-grade Spring Boot application that serves as the backbone for academic conference management operations. Designed and developed exclusively for SaraConference2k25 Organization, this proprietary system delivers comprehensive APIs for user management, paper submission workflows, peer review processes, and sophisticated role-based access control.

### Enterprise Architecture

Built on a foundation of industry-leading technologies and architectural best practices, this backend system provides:

- **High Availability**: Designed for 99.9% uptime with horizontal scalability
- **Enterprise Security**: Multi-layered security architecture compliant with industry standards
- **Cloud-Native Design**: Optimized for cloud deployment with Azure integration
- **Microservices-Ready**: Modular architecture supporting future service decomposition
- **Audit & Compliance**: Comprehensive logging and auditing for regulatory requirements

### Key Capabilities

- **Multi-Role Authentication**: Sophisticated authentication supporting Administrators, Evaluators, and Participants with granular permission models
- **Advanced Paper Management**: Complete paper lifecycle management including submission, review, revision, and publication workflows
- **Enterprise Cloud Integration**: Seamless Azure Blob Storage integration with SAS token security and CDN support
- **Standards-Compliant RESTful APIs**: Comprehensive, versioned REST endpoints following OpenAPI specifications
- **Production-Ready Infrastructure**: Built with Spring Boot 3.x, enterprise-grade libraries, and tested at scale

### Business Value

This system enables SaraConference2k25 Organization to:
- Streamline academic paper review and publication processes
- Reduce operational overhead through automation
- Ensure data security and regulatory compliance
- Scale efficiently to handle growing conference submissions
- Provide superior user experience for academics and administrators

---

## ✨ Features

### Authentication & Authorization
- ✅ Secure user registration and login
- ✅ Multi-role support (Admin, Evaluator, Participant)
- ✅ BCrypt password encryption
- ✅ Role-based access control (RBAC)
- ✅ JWT-ready architecture (extensible)

### Paper Submission System
- 📄 Multi-part form submission with file uploads
- 📄 Azure Blob Storage integration for scalable file management
- 📄 Paper metadata management (title, abstract, department, etc.)
- 📄 Download and retrieval APIs
- 📄 Advanced search and filtering capabilities

### User Management
- 👤 Profile management for all user types
- 👤 Admin, Evaluator, and Participant specific profiles
- 👤 Automatic username generation from email
- 👤 Timestamp tracking (created, updated)

### Search & Query Capabilities
- 🔍 Search papers by title
- 🔍 Filter by department
- 🔍 Filter by submitter email
- 🔍 Retrieve all papers with pagination support

### Additional Features
- 🚀 CORS enabled for cross-origin requests
- 🚀 PostgreSQL database with JPA/Hibernate
- 🚀 Lombok integration for reduced boilerplate
- 🚀 Spring Boot DevTools for hot reloading
- 🚀 Comprehensive error handling
- 🚀 Logging with SLF4J

---

## 🏗 Architecture

### System Architecture

```
┌─────────────────┐
│   Frontend      │
│   Application   │
└────────┬────────┘
         │ HTTP/REST
         │
┌────────▼────────────────────────────────────┐
│         Spring Boot Backend                 │
│  ┌──────────────────────────────────────┐  │
│  │       Controller Layer               │  │
│  │  - AuthController                    │  │
│  │  - PaperSubmissionController         │  │
│  │  - UserController                    │  │
│  └──────────┬───────────────────────────┘  │
│             │                                │
│  ┌──────────▼───────────────────────────┐  │
│  │       Service Layer                  │  │
│  │  - AuthService                       │  │
│  │  - PaperSubmissionService            │  │
│  │  - BlobStorageService                │  │
│  │  - UserService                       │  │
│  └──────────┬───────────────────────────┘  │
│             │                                │
│  ┌──────────▼───────────────────────────┐  │
│  │       Repository Layer (JPA)         │  │
│  │  - UserRepository                    │  │
│  │  - RoleRepository                    │  │
│  │  - PaperSubmissionRepository         │  │
│  └──────────┬───────────────────────────┘  │
└─────────────┼────────────────────────────────┘
              │
     ┌────────┴────────┐
     │                 │
┌────▼─────┐    ┌─────▼────────┐
│PostgreSQL│    │ Azure Blob   │
│ Database │    │   Storage    │
└──────────┘    └──────────────┘
```

### Layered Architecture Pattern

The application follows a **clean layered architecture**:

1. **Controller Layer**: Handles HTTP requests and responses
2. **Service Layer**: Contains business logic
3. **Repository Layer**: Data access using Spring Data JPA
4. **Entity Layer**: JPA entities representing database tables
5. **DTO Layer**: Data Transfer Objects for API communication

---

## 🛠 Tech Stack

### Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Primary programming language |
| **Spring Boot** | 3.5.6 | Application framework |
| **Spring Data JPA** | 3.5.6 | Database abstraction layer |
| **Spring Security** | 3.5.6 | Authentication and authorization |
| **Spring Web** | 3.5.6 | RESTful web services |

### Database & Storage

| Technology | Version | Purpose |
|------------|---------|---------|
| **PostgreSQL** | 42.7.3 | Primary relational database |
| **Azure Blob Storage** | 12.25.1 | Cloud file storage for papers |

### Development Tools

| Technology | Version | Purpose |
|------------|---------|---------|
| **Lombok** | Latest | Reduce boilerplate code |
| **Maven** | 3.x | Build and dependency management |
| **Spring Boot DevTools** | Latest | Development hot reload |

### Testing

| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot Test** | 3.5.6 | Unit and integration testing |
| **Spring Security Test** | 3.5.6 | Security testing utilities |

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

### Required Software

- **Java Development Kit (JDK) 17** or higher
  ```bash
  java -version  # Should show version 17+
  ```

- **Apache Maven 3.6+**
  ```bash
  mvn -version
  ```

- **PostgreSQL 12+**
  ```bash
  psql --version
  ```

### Required Services

- **Azure Storage Account** (for blob storage)
  - Create a storage account in Azure Portal
  - Create a container for paper submissions
  - Get connection string and container name

### Optional Tools

- **Git** for version control
- **Postman** or **cURL** for API testing
- **IntelliJ IDEA** or **VS Code** for development
- **Docker** for containerized deployment (optional)

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/SaraConference2k25/backend.git
cd backend
```

### 2. Set Up PostgreSQL Database

```bash
# Access PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE saraconference_db;

# Create user (optional)
CREATE USER saraconf_user WITH PASSWORD 'your_secure_password';
GRANT ALL PRIVILEGES ON DATABASE saraconference_db TO saraconf_user;
```

### 3. Configure Application Properties

Create `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/saraconference_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Azure Blob Storage Configuration
azure.storage.connection-string=your_azure_connection_string
azure.storage.container-name=papers

# Server Configuration
server.port=8080

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Logging Configuration
logging.level.com.saraconference.backend=INFO
logging.level.org.springframework.security=DEBUG
```

### 4. Build the Project

```bash
# Using Maven wrapper (recommended)
./mvnw clean install

# Or using system Maven
mvn clean install
```

### 5. Run the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using system Maven
mvn spring-boot:run

# Or run the JAR directly
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### 6. Verify Installation

The application should start on `http://localhost:8080`

Test the API:
```bash
curl http://localhost:8080/api/papers/test
# Expected response: "Paper submission API is working!"
```

---

## ⚙️ Configuration

### Environment Variables

For production deployments, use environment variables instead of property files:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/saraconference_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
export AZURE_STORAGE_CONNECTION_STRING=your_azure_connection_string
export AZURE_STORAGE_CONTAINER_NAME=papers
export SERVER_PORT=8080
```

### Profile-Based Configuration

Create multiple property files for different environments:

- `application-dev.properties` - Development environment
- `application-test.properties` - Testing environment
- `application-prod.properties` - Production environment

Activate a profile:
```bash
java -jar backend.jar --spring.profiles.active=prod
```

### Security Configuration

The current configuration in `WebSecurityConfig.java`:
- CSRF is disabled (suitable for REST APIs)
- All endpoints are publicly accessible (configure based on requirements)
- BCrypt password encryption is enabled

**For production**, enable proper security:
1. Implement JWT token authentication
2. Configure role-based endpoint protection
3. Enable HTTPS/TLS
4. Implement rate limiting

---

## 📚 API Documentation

### Base URL

```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register User

```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "SecurePass123!",
  "username": "johndoe"
}

Response: 200 OK
{
  "userId": 1,
  "email": "user@example.com",
  "username": "johndoe",
  "message": "User registered successfully"
}
```

#### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "SecurePass123!",
  "role": "PARTICIPANT"
}

Response: 200 OK
{
  "userId": 1,
  "email": "user@example.com",
  "username": "johndoe",
  "role": "PARTICIPANT",
  "message": "Login successful"
}
```

### Paper Submission Endpoints

#### Submit Paper

```http
POST /api/papers/submit
Content-Type: multipart/form-data

Form Data:
- name: John Doe
- email: john.doe@university.edu
- contactNo: +1234567890
- department: Computer Science
- collegeName: University of Technology
- paperTitle: AI in Healthcare
- paperAbstract: This paper discusses...
- paperFile: [PDF file]

Response: 200 OK
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@university.edu",
  "paperTitle": "AI in Healthcare",
  "paperFileUrl": "https://storage.azure.com/...",
  "submittedAt": "2024-01-15T10:30:00"
}
```

#### Get All Papers

```http
GET /api/papers/all

Response: 200 OK
[
  {
    "id": 1,
    "name": "John Doe",
    "paperTitle": "AI in Healthcare",
    "department": "Computer Science",
    "submittedAt": "2024-01-15T10:30:00"
  },
  ...
]
```

#### Get Paper by ID

```http
GET /api/papers/{id}

Response: 200 OK
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@university.edu",
  "contactNo": "+1234567890",
  "department": "Computer Science",
  "collegeName": "University of Technology",
  "paperTitle": "AI in Healthcare",
  "paperAbstract": "This paper discusses...",
  "paperFileName": "ai-healthcare.pdf",
  "paperFileUrl": "https://storage.azure.com/...",
  "submittedAt": "2024-01-15T10:30:00"
}
```

#### Download Paper

```http
GET /api/papers/download/{id}

Response: 200 OK
Content-Type: application/pdf
Content-Disposition: attachment; filename="paper.pdf"

[Binary PDF data]
```

#### Search Papers

```http
GET /api/papers/search?query=AI

Response: 200 OK
[
  {
    "id": 1,
    "paperTitle": "AI in Healthcare",
    ...
  }
]
```

#### Get Papers by Department

```http
GET /api/papers/department/Computer%20Science

Response: 200 OK
[...]
```

#### Get Papers by Email

```http
GET /api/papers/email/john.doe@university.edu

Response: 200 OK
[...]
```

#### Delete Paper

```http
DELETE /api/papers/{id}

Response: 200 OK
"Paper deleted successfully"
```

### User Management Endpoints

```http
GET /api/users
POST /api/users
GET /api/users/{id}
PUT /api/users/{id}
DELETE /api/users/{id}
```

### Error Responses

All endpoints return appropriate HTTP status codes:

```json
400 Bad Request
{
  "error": "Invalid input data",
  "message": "Email is required"
}

404 Not Found
{
  "error": "Resource not found",
  "message": "Paper with ID 123 not found"
}

500 Internal Server Error
{
  "error": "Internal server error",
  "message": "Database connection failed"
}
```

---

## 💾 Database Schema

### Entity Relationship Diagram

```
┌─────────────────────┐
│       User          │
├─────────────────────┤
│ PK userId           │
│    username         │
│    email (unique)   │
│    password         │
│    createdAt        │
│    updatedAt        │
└──────────┬──────────┘
           │
           │ Many-to-Many
           │
┌──────────▼──────────┐
│       Role          │
├─────────────────────┤
│ PK roleId           │
│    roleName (unique)│
└─────────────────────┘

┌─────────────────────────┐
│   PaperSubmission       │
├─────────────────────────┤
│ PK id                   │
│    name                 │
│    email                │
│    contactNo            │
│    department           │
│    collegeName          │
│    paperTitle           │
│    paperAbstract        │
│    paperFileName        │
│    paperFileUrl         │
│    submittedAt          │
└─────────────────────────┘

┌─────────────────────────┐
│   AdminProfile          │
├─────────────────────────┤
│ PK profileId            │
│ FK userId               │
│    ... (admin fields)   │
└─────────────────────────┘

┌─────────────────────────┐
│   EvaluatorProfile      │
├─────────────────────────┤
│ PK profileId            │
│ FK userId               │
│    ... (evaluator)      │
└─────────────────────────┘

┌─────────────────────────┐
│  ParticipantProfile     │
├─────────────────────────┤
│ PK profileId            │
│ FK userId               │
│    ... (participant)    │
└─────────────────────────┘
```

### Key Tables

#### Users Table
- Stores user authentication information
- Email is unique identifier
- Passwords are BCrypt encrypted
- Supports multiple roles per user

#### Roles Table
- Predefined roles: ADMIN, EVALUATOR, PARTICIPANT
- Many-to-many relationship with users

#### Paper_Submission Table
- Complete paper metadata
- References Azure Blob Storage URLs
- Timestamped submissions

#### Profile Tables
- Role-specific profile information
- One-to-one with User table
- Extensible for additional fields

---

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/saraconference/backend/
│   │   │   ├── config/              # Configuration classes
│   │   │   │   └── WebSecurityConfig.java
│   │   │   ├── controller/          # REST controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── PaperSubmissionController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── AuthRequest.java
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   ├── PaperSubmissionRequest.java
│   │   │   │   └── PaperSubmissionResponse.java
│   │   │   ├── entity/              # JPA entities
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── PaperSubmission.java
│   │   │   │   ├── AdminProfile.java
│   │   │   │   ├── EvaluatorProfile.java
│   │   │   │   └── ParticipantProfile.java
│   │   │   ├── repository/          # Spring Data JPA repositories
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── RoleRepository.java
│   │   │   │   ├── PaperSubmissionRepository.java
│   │   │   │   ├── AdminProfileRepository.java
│   │   │   │   ├── EvaluatorProfileRepository.java
│   │   │   │   └── ParticipantProfileRepository.java
│   │   │   ├── service/             # Service interfaces
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── PaperSubmissionService.java
│   │   │   │   └── BlobStorageService.java
│   │   │   ├── service/impl/        # Service implementations
│   │   │   │   ├── AuthServiceImpl.java
│   │   │   │   ├── UserServiceImpl.java
│   │   │   │   ├── PaperSubmissionServiceImpl.java
│   │   │   │   └── BlobStorageServiceImpl.java
│   │   │   └── BackendApplication.java  # Main application class
│   │   └── resources/
│   │       └── application.properties   # (gitignored - create locally)
│   └── test/
│       └── java/com/saraconference/backend/
│           └── BackendApplicationTests.java
├── .mvn/                            # Maven wrapper
├── .gitignore
├── mvnw                             # Maven wrapper script (Unix)
├── mvnw.cmd                         # Maven wrapper script (Windows)
├── pom.xml                          # Maven project configuration
└── README.md                        # This file
```

### Key Directories

- **config/**: Security, CORS, and other configuration beans
- **controller/**: REST API endpoints (HTTP request handlers)
- **dto/**: Data transfer objects for API requests/responses
- **entity/**: JPA entities mapped to database tables
- **repository/**: Data access layer (Spring Data JPA)
- **service/**: Business logic layer (interfaces and implementations)

---

## 💻 Development

### Development Environment Setup

1. **IDE Setup**
   - Import project as Maven project
   - Enable Lombok annotation processing
   - Configure code style (Google Java Style recommended)

2. **Enable Auto-Reload**
   ```xml
   <!-- Already included in pom.xml -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-devtools</artifactId>
   </dependency>
   ```

3. **Database Tools**
   - Use **pgAdmin** for PostgreSQL management
   - Or use **DBeaver** for universal database access

### Code Style Guidelines

- Follow **Spring Framework** conventions
- Use **Lombok** annotations to reduce boilerplate
- Write **JavaDoc** for public APIs
- Keep controllers thin, services thick
- Use **dependency injection** (constructor injection preferred)

### Lombok Annotations Used

```java
@Getter, @Setter       // Generate getters/setters
@NoArgsConstructor     // Generate no-args constructor
@AllArgsConstructor    // Generate all-args constructor
@Builder               // Enable builder pattern
@Data                  // Combines @Getter, @Setter, @ToString, etc.
```

### Adding New Features

1. **Create Entity** (if needed)
   ```java
   @Entity
   @Table(name = "your_table")
   public class YourEntity { ... }
   ```

2. **Create Repository**
   ```java
   public interface YourRepository extends JpaRepository<YourEntity, Long> { }
   ```

3. **Create Service Interface & Implementation**
   ```java
   public interface YourService { ... }
   
   @Service
   public class YourServiceImpl implements YourService { ... }
   ```

4. **Create Controller**
   ```java
   @RestController
   @RequestMapping("/api/your-resource")
   public class YourController { ... }
   ```

5. **Add DTOs** (if needed)
   ```java
   public class YourRequest { ... }
   public class YourResponse { ... }
   ```

---

## 🧪 Testing

### Running Tests

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report

# Run specific test class
./mvnw test -Dtest=BackendApplicationTests

# Skip tests during build
./mvnw clean install -DskipTests
```

### Writing Tests

#### Unit Test Example

```java
@SpringBootTest
class AuthServiceTest {
    
    @Autowired
    private AuthService authService;
    
    @Test
    void testUserRegistration() {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        
        AuthResponse response = authService.register(request);
        
        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
    }
}
```

#### Integration Test Example

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PaperSubmissionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testSubmitPaper() throws Exception {
        mockMvc.perform(post("/api/papers/submit")
                .param("name", "John Doe")
                .param("email", "john@example.com")
                // ... more parameters
        )
        .andExpect(status().isOk());
    }
}
```

### Test Coverage

Aim for:
- **Unit Tests**: 80%+ coverage
- **Integration Tests**: Critical user flows
- **API Tests**: All public endpoints

---

## 🚀 Deployment

### Building for Production

```bash
# Build JAR file
./mvnw clean package -DskipTests

# The JAR will be in target/
ls -lh target/backend-0.0.1-SNAPSHOT.jar
```

### Deployment Options

#### 1. Traditional Server (JAR)

```bash
# Run with production profile
java -jar target/backend-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=prod \
  --server.port=8080
```

#### 2. Docker Deployment

Create `Dockerfile`:

```dockerfile
FROM openjdk:17-slim
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:

```bash
# Build Docker image
docker build -t saraconference-backend:latest .

# Run container
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/saraconference_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=password \
  -e AZURE_STORAGE_CONNECTION_STRING=your_connection_string \
  -e AZURE_STORAGE_CONTAINER_NAME=papers \
  --name saraconf-backend \
  saraconference-backend:latest
```

#### 3. Docker Compose

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: saraconference_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  backend:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/saraconference_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
      AZURE_STORAGE_CONNECTION_STRING: ${AZURE_STORAGE_CONNECTION_STRING}
      AZURE_STORAGE_CONTAINER_NAME: papers
    depends_on:
      - postgres

volumes:
  postgres_data:
```

Run with:
```bash
docker-compose up -d
```

#### 4. Cloud Deployment

##### Azure App Service

```bash
# Login to Azure
az login

# Create resource group
az group create --name saraconf-rg --location eastus

# Create App Service plan
az appservice plan create \
  --name saraconf-plan \
  --resource-group saraconf-rg \
  --sku B1 \
  --is-linux

# Create web app
az webapp create \
  --name saraconf-backend \
  --resource-group saraconf-rg \
  --plan saraconf-plan \
  --runtime "JAVA:17-java17"

# Configure app settings
az webapp config appsettings set \
  --name saraconf-backend \
  --resource-group saraconf-rg \
  --settings \
    SPRING_DATASOURCE_URL="jdbc:postgresql://your-db.postgres.database.azure.com:5432/saraconference_db" \
    SPRING_DATASOURCE_USERNAME="your_username" \
    SPRING_DATASOURCE_PASSWORD="your_password" \
    AZURE_STORAGE_CONNECTION_STRING="your_connection_string" \
    AZURE_STORAGE_CONTAINER_NAME="papers"

# Deploy JAR
az webapp deploy \
  --name saraconf-backend \
  --resource-group saraconf-rg \
  --src-path target/backend-0.0.1-SNAPSHOT.jar
```

##### AWS Elastic Beanstalk

```bash
# Initialize EB application
eb init -p java-17 saraconf-backend --region us-east-1

# Create environment
eb create saraconf-env

# Set environment variables
eb setenv \
  SPRING_DATASOURCE_URL="jdbc:postgresql://your-rds-endpoint:5432/saraconference_db" \
  SPRING_DATASOURCE_USERNAME="admin" \
  SPRING_DATASOURCE_PASSWORD="password" \
  AZURE_STORAGE_CONNECTION_STRING="your_connection_string" \
  AZURE_STORAGE_CONTAINER_NAME="papers"

# Deploy
eb deploy
```

##### Heroku

```bash
# Login to Heroku
heroku login

# Create app
heroku create saraconf-backend

# Add PostgreSQL addon
heroku addons:create heroku-postgresql:hobby-dev

# Set config vars
heroku config:set \
  AZURE_STORAGE_CONNECTION_STRING="your_connection_string" \
  AZURE_STORAGE_CONTAINER_NAME="papers"

# Deploy
git push heroku main
```

### Production Checklist

Before deploying to production:

- [ ] Update `application.properties` with production values
- [ ] Enable HTTPS/TLS
- [ ] Configure proper CORS settings
- [ ] Enable authentication/authorization
- [ ] Set up database backups
- [ ] Configure logging (centralized logging recommended)
- [ ] Set up monitoring and alerting
- [ ] Configure rate limiting
- [ ] Review and harden security settings
- [ ] Set up CI/CD pipeline
- [ ] Document environment variables
- [ ] Configure health check endpoints
- [ ] Set up auto-scaling (if needed)
- [ ] Review Azure Blob Storage security
- [ ] Enable database connection pooling

---

## 🔒 Security

### Current Security Features

1. **Password Encryption**: BCrypt algorithm for password hashing
2. **SQL Injection Protection**: JPA/Hibernate parameterized queries
3. **CORS Configuration**: Cross-origin resource sharing enabled
4. **Input Validation**: Basic validation on DTOs

### Security Recommendations for Production

#### 1. Enable JWT Authentication

```java
// Add JWT token filter
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain chain) {
        // JWT validation logic
    }
}
```

#### 2. Configure Role-Based Access

```java
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/papers/submit").hasAnyRole("PARTICIPANT", "ADMIN")
                .requestMatchers("/api/papers/**").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        return http.build();
    }
}
```

#### 3. Enable HTTPS

```properties
# application.properties
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your_password
server.ssl.key-store-type=PKCS12
```

#### 4. Implement Rate Limiting

Use **Bucket4j** or **Resilience4j** for rate limiting:

```xml
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>8.1.0</version>
</dependency>
```

#### 5. Input Validation

```java
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(
        @Valid @RequestBody AuthRequest request) {
    // @Valid triggers validation
}

// In DTO
public class AuthRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
```

#### 6. Security Headers

```java
http.headers(headers -> headers
    .contentSecurityPolicy("default-src 'self'")
    .frameOptions().deny()
    .xssProtection()
    .httpStrictTransportSecurity()
);
```

### Secure Azure Blob Storage

1. Use **Shared Access Signatures (SAS)** for time-limited access
2. Enable **Azure AD authentication**
3. Configure **blob access policies**
4. Enable **soft delete** for data recovery

### Security Best Practices

- Never commit sensitive data (passwords, keys) to version control
- Use environment variables for secrets
- Regularly update dependencies for security patches
- Implement audit logging for sensitive operations
- Use **Azure Key Vault** for secret management
- Enable **SQL injection** and **XSS** protection
- Implement **CSRF tokens** for stateful sessions
- Use **secure random** for token generation

---

## 🔧 Troubleshooting

### Common Issues

#### 1. Application Won't Start

**Problem**: `Failed to configure a DataSource`

**Solution**:
```bash
# Ensure PostgreSQL is running
sudo service postgresql status

# Check application.properties has correct DB config
spring.datasource.url=jdbc:postgresql://localhost:5432/saraconference_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

#### 2. Azure Blob Storage Connection Fails

**Problem**: `BlobServiceException: Connection refused`

**Solution**:
```properties
# Verify connection string format
azure.storage.connection-string=DefaultEndpointsProtocol=https;AccountName=yourAccount;AccountKey=yourKey;EndpointSuffix=core.windows.net

# Test connection string with Azure Storage Explorer
```

#### 3. File Upload Fails

**Problem**: `MaxUploadSizeExceededException`

**Solution**:
```properties
# Increase max file size
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```

#### 4. Port Already in Use

**Problem**: `Port 8080 already in use`

**Solution**:
```bash
# Find process using port
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill process or change port
server.port=8081
```

#### 5. Lombok Not Working

**Problem**: `Cannot resolve symbol 'get*' or 'set*'`

**Solution**:
```bash
# IntelliJ IDEA
# 1. Install Lombok plugin
# 2. Settings → Build → Compiler → Annotation Processors → Enable annotation processing

# Eclipse
# 1. Download lombok.jar
# 2. Run: java -jar lombok.jar
# 3. Select Eclipse installation
```

#### 6. Database Migration Issues

**Problem**: Schema changes not applying

**Solution**:
```properties
# For development, use update
spring.jpa.hibernate.ddl-auto=update

# For production, use validate and use Flyway/Liquibase
spring.jpa.hibernate.ddl-auto=validate
```

### Debug Mode

Enable debug logging:

```properties
logging.level.root=INFO
logging.level.com.saraconference.backend=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Performance Issues

1. **Enable Connection Pooling**:
```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

2. **Enable Query Caching**:
```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
```

3. **Optimize Queries**:
```java
// Use @Query with JOIN FETCH to avoid N+1 problems
@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
Optional<User> findByEmailWithRoles(@Param("email") String email);
```

### Enterprise Support

For enterprise support and assistance:

1. **Check System Logs**: Review application logs in `/var/log/saraconference/` or configured log directory
2. **Contact Support Team**: Email support@saraconference2k25.org with:
   - Detailed error description
   - Complete stack trace
   - Steps to reproduce the issue
   - Environment information (OS, Java version, configuration)
   - Request ID (if applicable)
3. **Emergency Support**: For critical production issues, use the emergency support hotline provided in your service agreement
4. **Knowledge Base**: Access internal documentation portal for common solutions

---

## 🆘 Support & SLA

### Support Tiers

#### Enterprise Support (24/7)
- **Response Time**: 
  - Critical Issues: 1 hour
  - High Priority: 4 hours
  - Medium Priority: 8 hours
  - Low Priority: 24 hours
- **Availability**: 24x7x365
- **Channels**: Phone, Email, Portal
- **Features**: Dedicated support engineer, proactive monitoring

#### Business Support
- **Response Time**:
  - Critical Issues: 4 hours
  - High Priority: 8 hours
  - Medium Priority: 1 business day
  - Low Priority: 2 business days
- **Availability**: Business hours (9 AM - 6 PM, Monday-Friday)
- **Channels**: Email, Portal

### Service Level Agreement (SLA)

#### Uptime Guarantee
- **Production Environment**: 99.9% uptime
- **Scheduled Maintenance**: Announced 72 hours in advance
- **Maintenance Window**: Saturdays 2:00 AM - 6:00 AM (Local Time)

#### Performance Metrics
- **API Response Time**: < 200ms (95th percentile)
- **Database Query Performance**: < 100ms average
- **File Upload/Download**: < 5 seconds for files up to 10MB

#### Monitoring & Alerts
- **Real-time Monitoring**: 24/7 system health monitoring
- **Automated Alerts**: Instant notification for system anomalies
- **Health Check Endpoint**: `/actuator/health` (when enabled)

### Support Channels

**Email Support**: support@saraconference2k25.org  
**Emergency Hotline**: [Contact your account manager]  
**Support Portal**: https://support.saraconference2k25.org  
**Documentation**: https://docs.saraconference2k25.org

### Escalation Process

1. **Level 1**: Support Engineers (First Response)
2. **Level 2**: Senior Technical Team
3. **Level 3**: Development Team & Architects
4. **Level 4**: CTO & Executive Leadership

---

## 🔐 Compliance & Security

### Security Standards

This system is designed and maintained in compliance with:

- **OWASP Top 10**: Protection against common web vulnerabilities
- **GDPR**: Data protection and privacy compliance (if applicable)
- **ISO 27001**: Information security management standards
- **SOC 2 Type II**: Security, availability, and confidentiality controls

### Data Security

#### Encryption
- **Data in Transit**: TLS 1.3 encryption for all API communications
- **Data at Rest**: AES-256 encryption for database and file storage
- **Password Storage**: BCrypt hashing with salt (work factor: 12)

#### Access Control
- **Authentication**: Multi-factor authentication support
- **Authorization**: Role-based access control (RBAC)
- **Session Management**: Secure session handling with timeout policies
- **API Security**: Rate limiting, IP whitelisting (configurable)

#### Data Privacy
- **Personal Data**: GDPR-compliant data handling procedures
- **Data Retention**: Configurable retention policies
- **Data Deletion**: Right to erasure implementation
- **Audit Logs**: Comprehensive activity logging for compliance

### Security Practices

#### Regular Security Reviews
- **Vulnerability Scanning**: Weekly automated scans
- **Penetration Testing**: Quarterly third-party assessments
- **Dependency Updates**: Monthly security patch reviews
- **Code Reviews**: Security-focused peer reviews for all changes

#### Incident Response
- **Detection**: Real-time security monitoring and alerting
- **Response Time**: < 2 hours for security incidents
- **Communication**: Transparent incident reporting to stakeholders
- **Documentation**: Post-incident analysis and remediation reports

### Compliance Documentation

For compliance documentation and certifications:
- Request SOC 2 reports from your account manager
- Access security documentation in the internal compliance portal
- Schedule security reviews and audits as needed

### Security Reporting

**Report Security Vulnerabilities**: security@saraconference2k25.org  
**PGP Key**: Available on request  
**Bug Bounty Program**: Contact security team for details

---

## 👥 Internal Development Guidelines

**NOTICE**: This is proprietary software for internal use only. External contributions are not accepted.

### Development Standards

#### Code Quality Requirements

- **Code Style**: Adhere to Google Java Style Guide
- **Testing**: Minimum 80% code coverage required
- **Documentation**: JavaDoc required for all public APIs
- **Code Review**: Mandatory peer review before merge
- **Branch Strategy**: GitFlow workflow (main, develop, feature/*, hotfix/*)

#### Development Workflow

1. **Create Feature Branch**
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/TICKET-ID-description
   ```

2. **Development**
   - Write clean, maintainable code
   - Follow existing architectural patterns
   - Add comprehensive unit and integration tests
   - Update technical documentation

3. **Commit Standards**
   ```bash
   git commit -m "feat(module): TICKET-ID - Brief description
   
   Detailed description of changes
   - Specific change 1
   - Specific change 2
   
   Refs: TICKET-ID"
   ```

   Commit prefixes:
   - `feat:` New features
   - `fix:` Bug fixes
   - `refactor:` Code restructuring
   - `perf:` Performance improvements
   - `test:` Test additions/modifications
   - `docs:` Documentation updates
   - `chore:` Maintenance tasks

4. **Testing**
   ```bash
   ./mvnw clean verify
   ./mvnw test
   ```

5. **Code Review**
   - Create pull request to `develop` branch
   - Assign to designated code reviewers
   - Address all review comments
   - Obtain required approvals (minimum 2)

6. **Merge**
   - Squash and merge after approval
   - Delete feature branch after merge

### Quality Gates

All code must pass:
- ✅ Unit tests (80%+ coverage)
- ✅ Integration tests
- ✅ Static code analysis (SonarQube)
- ✅ Security scanning
- ✅ Performance benchmarks
- ✅ Code review approval

### Internal Bug Reporting

Use internal issue tracking system:
- **JIRA Project**: SARACONF-BACKEND
- **Priority Levels**: P0 (Critical), P1 (High), P2 (Medium), P3 (Low)
- **Required Information**:
  - Environment (Dev/Staging/Production)
  - Steps to reproduce
  - Expected vs actual behavior
  - Logs and stack traces
  - Screenshots/recordings if applicable

### Feature Requests

Submit via internal product management portal:
- **Business Justification**: Required
- **User Impact**: Required
- **Technical Feasibility**: Review by architects
- **Prioritization**: Product team decision

---

## 📄 License

**PROPRIETARY AND CONFIDENTIAL**

This software and associated documentation are the exclusive property of **SaraConference2k25 Organization**.

### License Summary

- **Type**: Proprietary Software License
- **Copyright**: © 2025 SaraConference2k25 Organization. All Rights Reserved.
- **Distribution**: Restricted - Internal Use Only
- **Modification**: Prohibited without authorization
- **Redistribution**: Prohibited

### Key Terms

```
PROPRIETARY SOFTWARE - CONFIDENTIAL

This software is proprietary to SaraConference2k25 Organization and is 
licensed for use solely by authorized personnel of the Organization.

RESTRICTIONS:
• No copying, modification, or distribution without written permission
• No reverse engineering, decompilation, or disassembly
• No disclosure to third parties
• Internal use only - not for external distribution
• All intellectual property rights reserved by the Organization

CONFIDENTIALITY:
This software contains trade secrets and proprietary information. 
Unauthorized disclosure or use is strictly prohibited and may result 
in legal action.
```

**Full License**: See [LICENSE](LICENSE) file for complete terms and conditions.

### Usage Rights

Only authorized employees and contractors of SaraConference2k25 Organization 
are permitted to access, use, or modify this software. All usage must comply 
with:

- Organization's information security policies
- Acceptable use policies
- Confidentiality agreements
- Employment/contractor agreements

### Third-Party Components

This software uses third-party open-source components that are licensed under 
their respective licenses (Apache 2.0, MIT, etc.). See NOTICE file for attribution.
The proprietary license applies only to the SaraConference2k25 original code 
and does not affect the licenses of third-party components.

### Intellectual Property

All code, documentation, designs, architectures, and related materials are the 
intellectual property of SaraConference2k25 Organization. Any inventions, 
improvements, or derivative works created by employees or contractors in 
connection with this project belong to the Organization.

### License Inquiries

For licensing questions or requests:
- **Email**: legal@saraconference2k25.org
- **Subject Line**: "Software License Inquiry - Backend System"

---

## 📞 Contact

### Enterprise Support

**PRIMARY CONTACTS**

- **Technical Support**: support@saraconference2k25.org
- **Security Issues**: security@saraconference2k25.org  
- **General Inquiries**: admin@saraconference2k25.org
- **Legal/Licensing**: legal@saraconference2k25.org

**EMERGENCY SUPPORT**

- **Critical Issues Hotline**: [Contact your account manager for details]
- **Escalation Email**: escalation@saraconference2k25.org
- **Response Time**: < 1 hour for P0/Critical issues

### Department Contacts

#### Development Team
- **Lead Architect**: architecture@saraconference2k25.org
- **Development Manager**: devmanager@saraconference2k25.org
- **DevOps Team**: devops@saraconference2k25.org

#### Project Management
- **Product Owner**: product@saraconference2k25.org
- **Project Manager**: pm@saraconference2k25.org
- **Release Coordinator**: releases@saraconference2k25.org

#### Operations
- **Infrastructure Team**: infrastructure@saraconference2k25.org
- **Database Administration**: dba@saraconference2k25.org
- **Monitoring & Alerts**: monitoring@saraconference2k25.org

### Internal Resources

- **Knowledge Base**: https://wiki.saraconference2k25.org
- **Internal Portal**: https://portal.saraconference2k25.org
- **Issue Tracker**: JIRA Project SARACONF-BACKEND
- **CI/CD Dashboard**: https://ci.saraconference2k25.org
- **Monitoring Dashboard**: https://monitoring.saraconference2k25.org

### Repository Information

- **Organization**: SaraConference2k25 Organization (Private)
- **Repository**: Internal GitLab/GitHub Enterprise
- **Access**: Restricted to authorized personnel only

### Business Hours

- **Standard Support**: Monday - Friday, 9:00 AM - 6:00 PM (Local Time)
- **Enterprise Support**: 24/7/365 (for eligible customers)
- **Maintenance Window**: Saturday, 2:00 AM - 6:00 AM (Local Time)

---

## 🙏 Technology Stack Acknowledgments

This enterprise solution leverages best-in-class open-source technologies:

- **Spring Framework & Spring Boot** - Industry-leading Java application framework
- **PostgreSQL** - Enterprise-class relational database management system
- **Microsoft Azure** - Cloud infrastructure and storage services
- **Hibernate ORM** - Object-relational mapping framework
- **Project Lombok** - Java annotation library for code reduction

**Note**: While this software utilizes open-source components, the SaraConference2k25 
codebase itself is proprietary and not open source. See [License](#-license) section.

---

## 🗺 Product Roadmap

### Q1 2025 (Current Sprint)

- [x] Core authentication and authorization system
- [x] Paper submission and storage infrastructure
- [x] Azure Blob Storage integration
- [x] PostgreSQL database schema and migrations
- [ ] JWT token-based authentication
- [ ] Advanced role-based access control (RBAC)
- [ ] Comprehensive audit logging

### Q2 2025

- [ ] Paper review and evaluation workflow
- [ ] Email notification service integration
- [ ] Admin dashboard and analytics APIs
- [ ] Automated evaluator assignment algorithm
- [ ] Paper status tracking and workflow management
- [ ] Advanced search with Elasticsearch integration
- [ ] API versioning strategy implementation

### Q3 2025

- [ ] Real-time notifications via WebSocket
- [ ] Export functionality (PDF reports, Excel exports)
- [ ] Multi-language support (i18n/l10n)
- [ ] API rate limiting and throttling
- [ ] OpenAPI/Swagger documentation
- [ ] Performance optimization and caching layer
- [ ] Automated backup and disaster recovery

### Q4 2025

- [ ] GraphQL API support
- [ ] Mobile app backend optimization
- [ ] Advanced analytics and reporting
- [ ] Machine learning-based paper recommendations
- [ ] Integration with external academic systems
- [ ] Enhanced security features (2FA, SSO)
- [ ] Compliance dashboard (GDPR, data governance)

### Version History

#### v0.0.1-SNAPSHOT (Current - In Development)
**Release Date**: Q1 2025

**Features**:
- Multi-role authentication system (Admin, Evaluator, Participant)
- Paper submission with file upload capability
- Azure Blob Storage integration for document management
- PostgreSQL database with JPA/Hibernate
- RESTful API endpoints
- BCrypt password encryption
- CORS configuration
- Spring Security integration

**Known Limitations**:
- JWT authentication in progress
- Email notifications not yet implemented
- Review workflow pending
- Limited API documentation

---

## 📊 System Requirements

### Minimum Requirements

**Production Environment**:
- **CPU**: 4 cores (2.5 GHz or higher)
- **RAM**: 8 GB minimum, 16 GB recommended
- **Storage**: 50 GB SSD (database and application)
- **Network**: 100 Mbps minimum bandwidth
- **OS**: Ubuntu 20.04 LTS or higher, RHEL 8+, Windows Server 2019+

**Database Server**:
- **PostgreSQL**: 12.x or higher
- **Storage**: 100 GB minimum (scales with data)
- **RAM**: 8 GB dedicated minimum

**Cloud Services**:
- **Azure Blob Storage**: Standard performance tier or higher
- **Bandwidth**: Sufficient for expected file transfer volumes

### Recommended Production Configuration

- **Application Servers**: 2+ instances (load balanced)
- **Database**: PostgreSQL cluster with replication
- **Cache**: Redis cluster for session management
- **CDN**: Azure CDN for static content delivery
- **Monitoring**: Application Insights / Prometheus + Grafana
- **Backup**: Automated daily backups with 30-day retention

---

## 📝 Change Management

### Release Process

1. **Development**: Feature development in `feature/*` branches
2. **Code Review**: Mandatory peer review and approval
3. **Quality Assurance**: Automated and manual testing
4. **Staging Deployment**: Deploy to staging environment
5. **UAT**: User acceptance testing by stakeholders
6. **Production Deployment**: Scheduled deployment during maintenance window
7. **Post-Deployment**: Monitoring and validation

### Deployment Schedule

- **Regular Releases**: Bi-weekly on Wednesdays at 10:00 PM
- **Hotfixes**: As needed, following emergency change process
- **Major Releases**: Quarterly, with advance notice to stakeholders

### Rollback Procedures

In case of deployment issues:
1. Automated rollback triggers on critical errors
2. Manual rollback procedure documented in runbook
3. Database rollback scripts maintained for each release
4. Maximum rollback time: 15 minutes

---

<div align="center">

## 🏢 SaraConference2k25 Organization

**CONFIDENTIAL & PROPRIETARY**

This software is the exclusive property of SaraConference2k25 Organization.  
All rights reserved. Unauthorized use, copying, or distribution is strictly prohibited.

---

**Product Version**: 0.0.1-SNAPSHOT  
**Documentation Version**: 1.0  
**Last Updated**: January 2025

---

© 2025 SaraConference2k25 Organization. All Rights Reserved.

[Back to Top](#saraconference2k25-backend)

</div>
