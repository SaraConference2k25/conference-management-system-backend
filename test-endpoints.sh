#!/bin/bash

# SARA Conference Backend API Test Script
# This script tests all paper submission and evaluator management endpoints

BASE_URL="http://localhost:8080/api"
ADMIN_TOKEN=""
EVALUATOR_TOKEN=""
USER_TOKEN=""

# Color codes for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}========================================${NC}"
echo -e "${YELLOW}SARA Conference Backend API Test Suite${NC}"
echo -e "${YELLOW}========================================${NC}\n"

# Test 1: Test Paper Submission API
echo -e "${YELLOW}[TEST 1] Testing Paper Submission API${NC}"
echo "Testing: GET /api/papers/test"
curl -X GET "$BASE_URL/papers/test" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 2: Submit a Paper
echo -e "${YELLOW}[TEST 2] Submitting a Paper${NC}"
echo "Testing: POST /api/papers/submit"
echo "Note: Replace /path/to/test.pdf with an actual PDF file"
curl -X POST "$BASE_URL/papers/submit" \
  -F "name=John Doe" \
  -F "email=john.doe@example.com" \
  -F "contactNo=+1-555-123-4567" \
  -F "department=Computer Science" \
  -F "collegeName=MIT" \
  -F "paperTitle=Machine Learning Applications" \
  -F "paperAbstract=This paper explores the applications of machine learning in modern computing systems" \
  -F "paperFile=@/tmp/sample.pdf" 2>/dev/null || echo "Note: PDF file not found at /tmp/sample.pdf"
echo -e "\n\n"

# Test 3: Get All Papers
echo -e "${YELLOW}[TEST 3] Getting All Papers${NC}"
echo "Testing: GET /api/papers/all"
curl -X GET "$BASE_URL/papers/all" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 4: Get Paper by ID
echo -e "${YELLOW}[TEST 4] Getting Paper by ID${NC}"
echo "Testing: GET /api/papers/1"
curl -X GET "$BASE_URL/papers/1" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 5: Search Papers
echo -e "${YELLOW}[TEST 5] Searching Papers${NC}"
echo "Testing: GET /api/papers/search?query=Machine"
curl -X GET "$BASE_URL/papers/search?query=Machine" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 6: Get Papers by Department
echo -e "${YELLOW}[TEST 6] Getting Papers by Department${NC}"
echo "Testing: GET /api/papers/department/Computer%20Science"
curl -X GET "$BASE_URL/papers/department/Computer%20Science" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 7: Get Papers by Email
echo -e "${YELLOW}[TEST 7] Getting Papers by Email${NC}"
echo "Testing: GET /api/papers/email/john.doe@example.com"
curl -X GET "$BASE_URL/papers/email/john.doe@example.com" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 8: Create Evaluator (Admin Only)
echo -e "${YELLOW}[TEST 8] Creating Evaluator (Admin)${NC}"
echo "Testing: POST /api/admin/evaluators/create"
curl -X POST "$BASE_URL/admin/evaluators/create" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "evaluator@institution.edu",
    "username": "evaluator1",
    "password": "SecurePass123!",
    "confirmPassword": "SecurePass123!",
    "department": "Computer Science"
  }'
echo -e "\n\n"

# Test 9: Get All Evaluators
echo -e "${YELLOW}[TEST 9] Getting All Evaluators${NC}"
echo "Testing: GET /api/admin/evaluators/all"
curl -X GET "$BASE_URL/admin/evaluators/all" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 10: Manage Evaluators
echo -e "${YELLOW}[TEST 10] Manage Evaluators${NC}"
echo "Testing: GET /api/admin/evaluators/manage"
curl -X GET "$BASE_URL/admin/evaluators/manage" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 11: Get Evaluator by ID
echo -e "${YELLOW}[TEST 11] Getting Evaluator by ID${NC}"
echo "Testing: GET /api/admin/evaluators/1"
curl -X GET "$BASE_URL/admin/evaluators/1" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 12: Update Evaluator
echo -e "${YELLOW}[TEST 12] Updating Evaluator${NC}"
echo "Testing: PUT /api/admin/evaluators/1"
curl -X PUT "$BASE_URL/admin/evaluators/1" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "evaluator.updated@institution.edu",
    "username": "evaluator1_updated",
    "password": "NewSecurePass123!",
    "confirmPassword": "NewSecurePass123!",
    "department": "Engineering"
  }'
echo -e "\n\n"

# Test 13: Get Evaluator Statistics
echo -e "${YELLOW}[TEST 13] Getting Evaluator Statistics${NC}"
echo "Testing: GET /api/admin/evaluators/stats/summary"
curl -X GET "$BASE_URL/admin/evaluators/stats/summary" \
  -H "Content-Type: application/json"
echo -e "\n\n"

# Test 14: Delete Evaluator
echo -e "${YELLOW}[TEST 14] Deleting Evaluator (Uncomment to test)${NC}"
echo "Testing: DELETE /api/admin/evaluators/1"
echo "# Uncomment the line below to delete an evaluator"
echo "# curl -X DELETE \"$BASE_URL/admin/evaluators/1\""
echo -e "\n\n"

# Test 15: Delete Paper
echo -e "${YELLOW}[TEST 15] Deleting Paper (Uncomment to test)${NC}"
echo "Testing: DELETE /api/papers/1"
echo "# Uncomment the line below to delete a paper"
echo "# curl -X DELETE \"$BASE_URL/papers/1\""
echo -e "\n\n"

echo -e "${YELLOW}========================================${NC}"
echo -e "${YELLOW}Test Suite Completed${NC}"
echo -e "${YELLOW}========================================${NC}"

