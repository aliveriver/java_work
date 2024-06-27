--CREATE DATABASE UNIVERSITY;
--创建数据库 UNIVERSITY
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS classes CASCADE;
DROP TABLE IF EXISTS universities CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS majors CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS admissions CASCADE;
DROP TABLE IF EXISTS Cousrses CASCADE;
DROP TABLE IF EXISTS EnrollmentMark CASCADE;
--创建表格
CREATE TABLE universities (
    university_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE majors (
    major_id INT PRIMARY KEY,
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
	FOREIGN KEY (department_id) REFERENCES departments(department_id),
	FOREIGN KEY (major_id) REFERENCES majors(major_id),
	FOREIGN KEY (university_id) REFERENCES universities( university_id) 
);
CREATE TABLE classes (
    class_id INT PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    major_id INT,
	University_id INT,
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
    --按照专业分班
);

CREATE TABLE students(
    student_id INT PRIMARY KEY,
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
    admission_id INT PRIMARY KEY,
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
    Courses_id INT PRIMARY KEY,
    major_id INT NOT NULL,
    department_id INT NOT NULL,
    Course_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);




--查询表格
SELECT * FROM universities ;
SELECT * FROM departments;
SELECT * FROM majors;
SELECT * FROM classes;
SELECT * FROM students;
SELECT * FROM  applications;
SELECT * FROM  admissions;
SELECT * FROM  Courses;
SELECT * FROM EnrollmentMark;
