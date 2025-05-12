-- Insert sample users
INSERT INTO users (username, email, password, role) VALUES
('test_user', 'test-user@gmail.com', '$2a$10$j6gvKVa.r2pFvAYzIpTh4um3fPQfm/mlBCykUVKRcI3fEnytmU/zy', 'user'),
('test_admin', 'test-admin.m@gmail.com', '$2a$10$JmndSWGspPOMvXpJYHpHnubCEkxCg/PWFhU/XLSquXqBtGjK36pYq', 'admin');

-- Insert sample categories
INSERT INTO categories (name) VALUES
('development'),
('design'),
('testing');

-- Insert sample records
-- user_id and category_id must correspond to existing values in the users and categories tables

INSERT INTO records (user_id, category_id, title, hours, date) VALUES
(1, 1, 'Build Login Page', 5, '2025-05-01'),
(1, 2, 'Design Landing Page', 3, '2025-05-02'),
(2, 1, 'Fix API Bug', 4, '2025-05-03'),
(2, 3, 'Write Unit Tests', 6, '2025-05-04'),
(2, 1, 'Integrate Backend', 7, '2025-05-05'),
(2, 2, 'Fix API Bug', 6, '2025-05-01'),
(1, 2, 'Write Unit Tests', 5, '2025-05-02'),
(2, 1, 'Fix Styling Issues', 4, '2025-05-03'),
(2, 1, 'Improve Test Coverage', 1, '2025-05-04'),
(1, 2, 'Fix Styling Issues', 1, '2025-05-05'),
(2, 3, 'Design Landing Page', 8, '2025-05-06'),
(1, 3, 'Integrate Backend', 3, '2025-04-01'),
(1, 3, 'Design Landing Page', 4, '2025-04-02'),
(1, 3, 'Update Documentation', 8, '2025-04-03'),
(1, 3, 'Integrate Backend', 5, '2025-04-04'),
(1, 1, 'Configure AWS ECS', 7, '2025-04-05'),
(1, 1, 'Read Clean Code', 8, '2025-04-06'),
(1, 1, 'Set Up RDS', 8, '2025-04-07'),
(1, 2, 'Debug OAuth Flow', 4, '2025-04-08'),
(1, 2, 'Debug OAuth Flow', 1, '2025-04-09'),
(1, 2, 'Update Documentation', 1, '2025-04-10');