-- Insert sample users
INSERT INTO users (username, email, password, role) VALUES
('janin', 'jnmanalili21@gmail.com', '$2a$10$j6gvKVa.r2pFvAYzIpTh4um3fPQfm/mlBCykUVKRcI3fEnytmU/zy', 'user'),
('janinadmin', 'janin.nicole.m@gmail.com', '$2a$10$JmndSWGspPOMvXpJYHpHnubCEkxCg/PWFhU/XLSquXqBtGjK36pYq', 'admin');

-- Insert sample categories
INSERT INTO categories (name) VALUES
('Development'),
('Design'),
('Testing');

-- Insert sample records
-- user_id and category_id must correspond to existing values in the users and categories tables

INSERT INTO records (user_id, category_id, title, hours, date) VALUES
(1, 1, 'Build Login Page', 5, '2025-05-01'),
(1, 2, 'Design Landing Page', 3, '2025-05-02'),
(2, 1, 'Fix API Bug', 4, '2025-05-03'),
(2, 3, 'Write Unit Tests', 6, '2025-05-04'),
(2, 1, 'Integrate Backend', 7, '2025-05-05');