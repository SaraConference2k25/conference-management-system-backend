# SARA Conference Backend - API Testing Guide

## Overview
This document provides comprehensive testing instructions for all endpoints in the SARA Conference Backend system.

## Prerequisites
1. Java 24+
2. PostgreSQL database running
3. Azure Storage Account configured
4. Spring Boot 3.5.6 running on port 8080

## Base URL
```
http://localhost:8080/api
```

---

## PART 1: PAPER SUBMISSION ENDPOINTS

### 1.1 Test API Connection
**Endpoint:** `GET /papers/test`

**Description:** Test if the paper submission API is working

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/test" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
"Paper submission API is working!"
```

---

### 1.2 Submit a Paper
**Endpoint:** `POST /papers/submit`

**Description:** Submit a new research paper with metadata and PDF file

**Form Parameters:**
- `name` (String, required): Full name of the author
- `email` (String, required): Email address
- `contactNo` (String, required): Phone number
- `department` (String, required): Department name
- `collegeName` (String, required): College/Institution name
- `paperTitle` (String, required): Title of the paper
- `paperAbstract` (String, required): Abstract of the paper
- `paperFile` (File, required): PDF file

**cURL Command:**
```bash
curl -X POST "http://localhost:8080/api/papers/submit" \
  -F "name=Alice Smith" \
  -F "email=alice.smith@university.edu" \
  -F "contactNo=+1-555-987-6543" \
  -F "department=Computer Science" \
  -F "collegeName=Stanford University" \
  -F "paperTitle=Quantum Computing Applications" \
  -F "paperAbstract=This research explores quantum computing principles and their applications in cryptography" \
  -F "paperFile=@/path/to/your/paper.pdf"
```

**Expected Response:**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@university.edu",
  "contactNo": "+1-555-987-6543",
  "department": "Computer Science",
  "collegeName": "Stanford University",
  "paperTitle": "Quantum Computing Applications",
  "paperAbstract": "This research explores quantum computing...",
  "paperFileUrl": "https://stellarstacker.blob.core.windows.net/papers/uuid_filename.pdf",
  "paperFileName": "paper.pdf",
  "submittedAt": "2025-11-01T15:30:45.123"
}
```

---

### 1.3 Get All Papers
**Endpoint:** `GET /papers/all`

**Description:** Retrieve all submitted papers

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/all" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice.smith@university.edu",
    "paperTitle": "Quantum Computing Applications",
    ...
  },
  {
    "id": 2,
    "name": "Bob Johnson",
    "email": "bob.johnson@university.edu",
    "paperTitle": "AI in Healthcare",
    ...
  }
]
```

---

### 1.4 Get Paper by ID
**Endpoint:** `GET /papers/{id}`

**Description:** Retrieve a specific paper by its ID

**Path Parameters:**
- `id` (Long): Paper ID

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/1" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@university.edu",
  "paperTitle": "Quantum Computing Applications",
  ...
}
```

---

### 1.5 Search Papers by Title
**Endpoint:** `GET /papers/search`

**Description:** Search papers by title (case-insensitive)

**Query Parameters:**
- `query` (String): Search query

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/search?query=Quantum" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "paperTitle": "Quantum Computing Applications",
    ...
  }
]
```

---

### 1.6 Get Papers by Department
**Endpoint:** `GET /papers/department/{department}`

**Description:** Retrieve papers from a specific department

**Path Parameters:**
- `department` (String): Department name (URL encoded)

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/department/Computer%20Science" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "department": "Computer Science",
    "paperTitle": "Quantum Computing Applications",
    ...
  }
]
```

---

### 1.7 Get Papers by Email
**Endpoint:** `GET /papers/email/{email}`

**Description:** Retrieve all papers submitted by a specific email

**Path Parameters:**
- `email` (String): Email address

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/email/alice.smith@university.edu" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "email": "alice.smith@university.edu",
    "paperTitle": "Quantum Computing Applications",
    ...
  }
]
```

---

### 1.8 Download Paper File
**Endpoint:** `GET /papers/download/{id}`

**Description:** Download the PDF file of a submitted paper

**Path Parameters:**
- `id` (Long): Paper ID

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/papers/download/1" \
  -H "Content-Type: application/json" \
  -o downloaded_paper.pdf
```

**Response:** Binary PDF file

---

### 1.9 Delete Paper
**Endpoint:** `DELETE /papers/{id}`

**Description:** Delete a paper and its associated file from blob storage

**Path Parameters:**
- `id` (Long): Paper ID

**cURL Command:**
```bash
curl -X DELETE "http://localhost:8080/api/papers/1" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
"Paper deleted successfully"
```

---

## PART 2: EVALUATOR MANAGEMENT ENDPOINTS

### 2.1 Create Evaluator (Admin Only)
**Endpoint:** `POST /admin/evaluators/create`

**Description:** Create a new evaluator account

**Request Body:**
```json
{
  "email": "evaluator@institution.edu",
  "username": "evaluator1",
  "password": "SecurePass123!",
  "confirmPassword": "SecurePass123!",
  "department": "Computer Science"
}
```

**cURL Command:**
```bash
curl -X POST "http://localhost:8080/api/admin/evaluators/create" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "evaluator@institution.edu",
    "username": "evaluator1",
    "password": "SecurePass123!",
    "confirmPassword": "SecurePass123!",
    "department": "Computer Science"
  }'
```

**Expected Response:**
```json
{
  "userId": 1,
  "username": "evaluator1",
  "email": "evaluator@institution.edu",
  "department": "Computer Science",
  "status": "Active",
  "workload": 0
}
```

**Notes:**
- Requires admin role
- Passwords must match
- Email must be unique
- Username must be unique

---

### 2.2 Get All Evaluators
**Endpoint:** `GET /admin/evaluators/all`

**Description:** Retrieve all evaluators in the system

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/admin/evaluators/all" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "userId": 1,
    "username": "evaluator1",
    "email": "evaluator@institution.edu",
    "department": "Computer Science",
    "status": "Active",
    "workload": 0
  },
  {
    "userId": 2,
    "username": "evaluator2",
    "email": "evaluator2@institution.edu",
    "department": "Engineering",
    "status": "Active",
    "workload": 5
  }
]
```

---

### 2.3 Manage Evaluators
**Endpoint:** `GET /admin/evaluators/manage`

**Description:** Get evaluators for management interface

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/admin/evaluators/manage" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
[
  {
    "userId": 1,
    "username": "evaluator1",
    "email": "evaluator@institution.edu",
    "department": "Computer Science",
    "status": "Active",
    "workload": 0
  }
]
```

---

### 2.4 Get Evaluator by ID
**Endpoint:** `GET /admin/evaluators/{id}`

**Description:** Retrieve a specific evaluator by ID

**Path Parameters:**
- `id` (Long): Evaluator ID

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/admin/evaluators/1" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "userId": 1,
  "username": "evaluator1",
  "email": "evaluator@institution.edu",
  "department": "Computer Science",
  "status": "Active",
  "workload": 0
}
```

---

### 2.5 Update Evaluator
**Endpoint:** `PUT /admin/evaluators/{id}`

**Description:** Update evaluator information

**Path Parameters:**
- `id` (Long): Evaluator ID

**Request Body:**
```json
{
  "email": "evaluator.updated@institution.edu",
  "username": "evaluator1_updated",
  "department": "Engineering"
}
```

**cURL Command:**
```bash
curl -X PUT "http://localhost:8080/api/admin/evaluators/1" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "evaluator.updated@institution.edu",
    "username": "evaluator1_updated",
    "password": "NewPass123!",
    "confirmPassword": "NewPass123!",
    "department": "Engineering"
  }'
```

**Expected Response:**
```json
{
  "userId": 1,
  "username": "evaluator1_updated",
  "email": "evaluator.updated@institution.edu",
  "department": "Engineering",
  "status": "Active",
  "workload": 0
}
```

---

### 2.6 Delete Evaluator
**Endpoint:** `DELETE /admin/evaluators/{id}`

**Description:** Delete an evaluator account

**Path Parameters:**
- `id` (Long): Evaluator ID

**cURL Command:**
```bash
curl -X DELETE "http://localhost:8080/api/admin/evaluators/1" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "message": "Evaluator deleted successfully"
}
```

---

### 2.7 Get Evaluator Statistics
**Endpoint:** `GET /admin/evaluators/stats/summary`

**Description:** Get summary statistics about evaluators

**cURL Command:**
```bash
curl -X GET "http://localhost:8080/api/admin/evaluators/stats/summary" \
  -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "totalEvaluators": 5,
  "activeEvaluators": 5,
  "availableEvaluators": 5
}
```

---

## Quick Test Sequence

### For Paper Submission Testing:
1. Test API: `curl http://localhost:8080/api/papers/test`
2. Submit paper: Use the submit endpoint with a PDF file
3. Get all papers: `curl http://localhost:8080/api/papers/all`
4. Search papers: `curl "http://localhost:8080/api/papers/search?query=test"`

### For Evaluator Management Testing:
1. Create evaluator: `POST /admin/evaluators/create`
2. Get all evaluators: `GET /admin/evaluators/all`
3. Update evaluator: `PUT /admin/evaluators/{id}`
4. Get stats: `GET /admin/evaluators/stats/summary`

---

## Error Handling

### Common Error Responses:

**400 Bad Request:**
```json
{
  "error": "Passwords do not match"
}
```

**404 Not Found:**
```json
{
  "error": "Paper not found: No paper with ID 999"
}
```

**500 Internal Server Error:**
```json
{
  "error": "Error submitting paper: File upload failed"
}
```

---

## Notes
- All endpoints support CORS for frontend integration
- File upload limit: 10MB
- Maximum request size: 10MB
- All timestamps are in ISO 8601 format
- Papers are stored in Azure Blob Storage
- Evaluator passwords are hashed with BCrypt


