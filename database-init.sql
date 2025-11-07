-- SARA Conference Backend - Database Initialization Script
-- Run this script to initialize roles and test data

-- =====================================================
-- 1. CREATE ROLES
-- =====================================================
INSERT INTO roles (role_name) VALUES ('admin')
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO roles (role_name) VALUES ('evaluator')
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO roles (role_name) VALUES ('participant')
ON CONFLICT (role_name) DO NOTHING;

-- =====================================================
-- 2. CREATE TEST ADMIN USER
-- =====================================================
-- Password: Admin@123456 (bcrypt hashed)
INSERT INTO users (username, email, password, created_at, updated_at, department, workload)
VALUES (
  'admin1',
  'admin@saraconference.edu',
  '$2a$10$slYQmyNdGzin7olVZiYm2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMm2',
  NOW(),
  NOW(),
  'Administration',
  0
)
ON CONFLICT (email) DO NOTHING;

-- Assign admin role to admin user
INSERT INTO user_roles (user_id, role_id)
SELECT u.user_id, r.role_id
FROM users u, roles r
WHERE u.email = 'admin@saraconference.edu' AND r.role_name = 'admin'
ON CONFLICT DO NOTHING;

-- =====================================================
-- 3. CREATE TEST EVALUATORS
-- =====================================================
-- Password: Evaluator@123 (bcrypt hashed)
INSERT INTO users (username, email, password, created_at, updated_at, department, workload)
VALUES
(
  'evaluator1',
  'evaluator1@institution.edu',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeQJj7vF8DwQcnmEDm.',
  NOW(),
  NOW(),
  'Computer Science',
  3
),
(
  'evaluator2',
  'evaluator2@institution.edu',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeQJj7vF8DwQcnmEDm.',
  NOW(),
  NOW(),
  'Engineering',
  5
),
(
  'evaluator3',
  'evaluator3@institution.edu',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeQJj7vF8DwQcnmEDm.',
  NOW(),
  NOW(),
  'Mathematics',
  2
)
ON CONFLICT (email) DO NOTHING;

-- Assign evaluator role to evaluators
INSERT INTO user_roles (user_id, role_id)
SELECT u.user_id, r.role_id
FROM users u, roles r
WHERE u.email IN ('evaluator1@institution.edu', 'evaluator2@institution.edu', 'evaluator3@institution.edu')
AND r.role_name = 'evaluator'
ON CONFLICT DO NOTHING;

-- =====================================================
-- 4. CREATE TEST PARTICIPANTS
-- =====================================================
-- Password: Participant@123 (bcrypt hashed)
INSERT INTO users (username, email, password, created_at, updated_at, department, workload)
VALUES
(
  'participant1',
  'participant1@university.edu',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeQJj7vF8DwQcnmEDm.',
  NOW(),
  NOW(),
  'Computer Science',
  0
),
(
  'participant2',
  'participant2@university.edu',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeQJj7vF8DwQcnmEDm.',
  NOW(),
  NOW(),
  'Engineering',
  0
)
ON CONFLICT (email) DO NOTHING;

-- Assign participant role to participants
INSERT INTO user_roles (user_id, role_id)
SELECT u.user_id, r.role_id
FROM users u, roles r
WHERE u.email IN ('participant1@university.edu', 'participant2@university.edu')
AND r.role_name = 'participant'
ON CONFLICT DO NOTHING;

-- =====================================================
-- 5. VERIFY DATA
-- =====================================================
-- Run these queries to verify the setup:

-- View all roles
SELECT * FROM roles;

-- View all users with roles
SELECT u.user_id, u.username, u.email, u.department,
       STRING_AGG(r.role_name, ', ') as roles
FROM users u
LEFT JOIN user_roles ur ON u.user_id = ur.user_id
LEFT JOIN roles r ON ur.role_id = r.role_id
GROUP BY u.user_id, u.username, u.email, u.department
ORDER BY u.user_id;

-- View total counts
SELECT
  (SELECT COUNT(*) FROM users) as total_users,
  (SELECT COUNT(*) FROM roles) as total_roles,
  (SELECT COUNT(*) FROM user_roles) as total_user_role_mappings;

-- View users by role
SELECT r.role_name, COUNT(u.user_id) as user_count
FROM roles r
LEFT JOIN user_roles ur ON r.role_id = ur.role_id
LEFT JOIN users u ON ur.user_id = u.user_id
GROUP BY r.role_name;

-- =====================================================
-- 6. CLEAN UP (Optional - only run if needed)
-- =====================================================
-- DELETE FROM user_roles;
-- DELETE FROM users;
-- DELETE FROM roles;
-- RESTART IDENTITY CASCADE;

