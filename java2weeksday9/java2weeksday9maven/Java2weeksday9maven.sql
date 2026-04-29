-- DATABASE student_management
CREATE DATABASE student_management;
USE student_management;
-- SAFE MODE
-- SET SQL_SAFE_UPDATES = 0; -- tắt
-- SET SQL_SAFE_UPDATES = 1; -- bật
-- TABLE student
CREATE TABLE student(
	id INT AUTO_INCREMENT PRIMARY KEY, 
    student_name VARCHAR(50) CHARACTER SET utf8mb4, -- tiếng Việt có dấu/ Anh/Trung/ Nhật/ Hàn/ Emoji 😄🔥❤️
    age INT CHECK (age >= 18), -- age buộc >= 18 thì mới insert/ update
    email VARCHAR(100) UNIQUE CHECK (email LIKE '%@gmail.com' OR email LIKE '%@email.com'), 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- INSERT INTO student
INSERT INTO student (student_name, age, email) VALUES ('Sú', 18, 'su@gmail.com');
INSERT INTO student (student_name, age, email) VALUES ('Sú', 18, 'su@email.com');
INSERT INTO student (student_name, age, email) VALUES ('Sala', 20, 'sala@email.com');
SELECT * FROM student;
Drop database student_management;
SELECT COUNT(*) FROM student; 