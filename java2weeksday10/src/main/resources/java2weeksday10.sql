-- DATABASE student_management
CREATE DATABASE student_management;
USE student_management;
-- SAFE MODE
-- SET SQL_SAFE_UPDATES = 0; -- tắt
-- SET SQL_SAFE_UPDATES = 1; -- bật
-- TABLE student
CREATE TABLE student(
	id VARCHAR(5) CHECK (id LIKE 'SV%') PRIMARY KEY, 
	name VARCHAR(50) CHARACTER SET utf8mb4, -- tiếng Việt có dấu/ Anh/Trung/ Nhật/ Hàn/ Emoji 😄🔥❤️
    age INT CHECK (age >= 18), -- age buộc >= 18 thì mới insert/ update
    email VARCHAR(100) UNIQUE CHECK (email LIKE '%@gmail.com' OR email LIKE '%@email.com')
);
-- INSERT INTO student
INSERT INTO student (id, name, age, email) VALUES ('SV001','Sú', 18, 'su@gmail.com');
SELECT * FROM student;