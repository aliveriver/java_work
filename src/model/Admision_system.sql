--CREATE DATABASE UNIVERSITY;
--创建数据库 UNIVERSITY
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS classes CASCADE;
DROP TABLE IF EXISTS universities CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS majors CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS admissions CASCADE;
DROP TABLE IF EXISTS Courses CASCADE;
DROP TABLE IF EXISTS EnrollmentMark CASCADE;




DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS admissions CASCADE;
DROP TABLE IF EXISTS students CASCADE;
--创建表格
CREATE TABLE universities (
    university_id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);
CREATE TABLE departments (
    department_id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE majors (
    major_id serial PRIMARY KEY,
    department_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);
CREATE TABLE EnrollmentMark(
	Enrollment_id INT PRIMARY KEY,
	university_id INT NOT NULL,
	department_id INT NOT NULL,
	major_id INT NOT NULL,
	RequiredScore INT NOT NULL,
	MRequiredN INT NOT NULL,
	DRequiredN INT NOT NULL,
	FOREIGN KEY (department_id) REFERENCES departments(department_id),
	FOREIGN KEY (major_id) REFERENCES majors(major_id),
	FOREIGN KEY (university_id) REFERENCES universities( university_id) 
);
CREATE TABLE classes (
    class_id serial PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    major_id INT,
	University_id INT,
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
    --按照专业分班
);

CREATE TABLE students(
    student_id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    score INT NOT NULL,
    class_id INT,
	FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

CREATE TABLE applications (
    --志愿表
    application_id INT PRIMARY KEY,
    student_id INT NOT NULL,
    university_id INT NOT NULL,
    department_id INT NOT NULL,
    major_id INT NOT NULL,
    is_adjustment INT NOT NULL,
    --是否调剂
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (university_id) REFERENCES universities(university_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);
CREATE TABLE admissions (
    --录取表
    admission_id serial  PRIMARY KEY,
    student_id INT NOT NULL,
    university_id INT NOT NULL,
    department_id INT NOT NULL,
    major_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (university_id) REFERENCES universities(university_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);
CREATE TABLE Courses (
    --学生兴趣表
    Courses_id serial PRIMARY KEY,
    major_id INT NOT NULL,
    department_id INT NOT NULL,
    Course_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);



-- 1--10添加
--查询表格
SELECT * FROM universities;
SELECT * FROM departments;
SELECT * FROM majors;
SELECT * FROM classes;
SELECT * FROM students;
SELECT * FROM  applications;
SELECT * FROM  admissions;
SELECT * FROM  Courses;
SELECT * FROM EnrollmentMark;
SELECT * FROM EnrollmentMark where   university_id =5 and department_id =4 ;
select * from applications where student_id = 3;


--一轮测试数据

DROP TABLE IF EXISTS admissions CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students(
    student_id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    score INT NOT NULL,
    class_id INT,
	FOREIGN KEY (class_id) REFERENCES classes(class_id)
);
CREATE TABLE admissions (
    --录取表
    admission_id serial  PRIMARY KEY,
    student_id INT NOT NULL,
    university_id INT NOT NULL,
    department_id INT NOT NULL,
    major_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (university_id) REFERENCES universities(university_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);
CREATE TABLE applications (
    --志愿表
    application_id INT PRIMARY KEY,
    student_id INT NOT NULL,
    university_id INT NOT NULL,
    department_id INT NOT NULL,
    major_id INT NOT NULL,
    is_adjustment INT NOT NULL,
    --是否调剂
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (university_id) REFERENCES universities(university_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);
INSERT INTO students (name, gender, age, score, class_id) VALUES
('伊雷娜', 'Female', 18, 720, null),
( '李龙', 'Male', 18, 721, null),
('周树', 'Male', 19, 723, null);
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(1,1,5,6,18,1),
(2,2,5,6,18,0),
(3,3,5,6,18,0);

--Peking University
--Finance   Economics
SELECT * FROM applications;
SELECT * FROM classes;
SELECT * FROM admissions;
SELECT * FROM majors;