/*
Day 8: Database – SQL cơ bản

1. SQL
    - SELECT: lấy dữ liệu từ bảng 	
			Công thức:	SELECT *(lấy tất cả cột trong bảng) hoặc column1, column2(lấy dữ liệu cột column1 và cột column2 trong bảng)
						FROM table_name (lấy dữ liệu ở bảng tên table_name)
						WHERE condition; (điều kiện lấy dữ liệu - nếu có) (dấu ; kết thúc câu lệnh)
	
    - INSERT: thêm dữ liệu mới vào bảng
			Công thức:	INSERT INTO table_name (thêm dữ liệu mới vào bảng tên table_name)
						 (thêm dữ liệu vào bảng) hoặc (column1, column2) (thêm dữ liệu vào cột column1 và cột column2 của bảng)
						VALUES (value1, value2); (thêm dữ liệu value1 vào cột column1 và dữ liệu value2 cột column2) (dấu ; kết thúc câu lệnh)
            
    - UPDATE: sửa chữa dữ liệu trong bảng
			Công thức:	UPDATE table_name (sửa chữa dữ liệu ở bảng tên table_name)
						SET column1 = value1 ( sửa chữa dữ liệu của cột column1 thành value1)
						WHERE condition; (điều kiện lấy dữ liệu - nếu có) (dấu ; kết thúc câu lệnh)
            
    - DELETE: xóa dữ liệu trong bảng
			Công thức:	DELETE FROM table_name (xóa dữ liệu ở bảng tên table_name)
						WHERE condition; (điều kiện lấy dữ liệu - nếu có) (dấu ; kết thúc câu lệnh)
            
2. Thiết kế bảng Student
    - Database: cơ sở dữ liệu chứa nhiều bảng dữ liệu (Table)
			Công thức:
				+ CREATE DATABASE: tạo database mới
						CREATE DATABASE database_name; (tạo database tên database_name) (dấu ; kết thúc câu lệnh)
				+ USE: sử dụng database 
						USE database_name; (sử dụng database tên database_name) (dấu ; kết thúc câu lệnh)
                + DROP DATABASE: xóa cả database
						DROP DATABASE database_name; (xóa database tên database_name) (dấu ; kết thúc câu lệnh)
                
	- Table: bảng chứa dòng dữ liệu (Row) và chứa thuộc tính (Column)
			Công thức:
				+ CREATE TABLE: tạo bảng mới
						CREATE TABLE table_name (tạo bảng mới tên table_name)
                        (column1 datatype constraint, (column1 có kiểu dữ liệu và rảng buộc - nếu có)
						column2 datatype constraint); (column2 có kiểu dữ liệu và rảng buộc - nếu có) (dấu ; kết thúc câu lệnh)
                + DROP TABLE: xóa bảng
						DROP TABLE table_name; (xóa bảng tên table_name) (dấu ; kết thúc câu lệnh)
                
	- Primary Key: ràng buộc khóa chính dùng để phân biệt mỗi dòng (Row) trong bảng (Table)
			Công thức:
				+ Primary key 1 cột:
						column1 datatype Primary key (column1 có kiểu dữ liệu và rảng buộc Primary key)
                        hoặc
                        Primary key (column1) (rảng buộc Primary key là column1)
                + Primary key nhiều cột 
						Primary key (column1,column2) (rảng buộc Primary key là column1 và column2)
                        
	- AUTO_INCREMENT: tự động tăng giá trị (1, 2, 3,...) cho cột kiểu số (INT, BIGINT, …), mỗi bảng chỉ có 1 cột, thường đi với PRIMARY KEY
			Công thức: 
				+ Tạo ngay trong CREATE TABLE:
						column1 datatype AUTO_INCREMENT constraint (column1 có kiểu dữ liệu, AUTO_INCREMENT và rảng buộc - nếu có)
                + Tùy chỉnh số:
						ALTER TABLE table_name AUTO_INCREMENT = 100; (Lần insert tiếp theo của bảng tên table_name sẽ là 100)
                
	- UNIQUE: ràng buộc đảm bảo giá trị trong cột không bị trùng (Column)
			Công thức: 
				+ Unique 1 cột: column1 datatype Unique (column1 có kiểu dữ liệu và rảng buộc Unique)
                + Unique nhiều cột: Unique (column1, column2) (rảng buộc Unique là column1 và column2)
                
	- TIMESTAMP: là kiểu dữ liệu dùng để lưu ngày + giờ
			Công thức: 
				+ Tạo ngay trong CREATE TABLE: 
						column1 Timestamp DEFAULT CURRENT_TIMESTAMP (column1 có kiểu dữ liệu là Timestamp, tự động lấy thời gian hiện tại khi INSERT)
                        hoặc
						column2 Timestamp (column2 có kiểu dữ liệu là Timestamp)
                        DEFAULT CURRENT_TIMESTAMP (tự động lấy thời gian hiện tại khi INSERT) 
                        ON UPDATE CURRENT_TIMESTAMP (khi UPDATE thì tự cập nhật lại giờ mới)
*/
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
Bài tập: Quản lý sinh viên (SQL)
Yêu cầu:
    1. Tạo database student_management.
    2. Tạo bảng student (id, name, age, email, created_at).
    3. Thêm dữ liệu sinh viên.
    4. Truy vấn danh sách sinh viên theo điều kiện.
    5. Cập nhật thông tin sinh viên.
    6. Xóa sinh viên.
    
Mục tiêu:
    - Biết thao tác CRUD (SELECT, INSERT, UPDATE, DELETE)
    - Hiểu cách thiết kế bảng trong database
    - Làm quen với SQL thực tế
*/
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
-- SELECT * FROM student
SELECT * FROM student WHERE age >= 20;
SELECT * FROM student WHERE email LIKE '%email.com';
SELECT * FROM student WHERE email LIKE '%gmail.com';
-- UPDATE student
UPDATE student SET student_name = 'su' WHERE email LIKE 'su@email.com';
SELECT * FROM student;
-- DETELE FROM student
DELETE FROM student WHERE id = 1;
SELECT * FROM student;