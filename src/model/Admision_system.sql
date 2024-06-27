CREATE DATABASE UNIVERSITY;
--创建数据库 UNIVERSITY
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS classes CASCADE;
DROP TABLE IF EXISTS universities CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS majors CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS admissions CASCADE;
DROP TABLE IF EXISTS Cousrses CASCADE;
--创建表格
CREATE TABLE universities (
    university_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    university_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (university_id) REFERENCES universities(university_id)
);
CREATE TABLE majors (
    major_id INT PRIMARY KEY,
    department_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);
CREATE TABLE classes (
    class_id INT PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    major_id INT,
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
    is_adjustment BOOLEAN NOT NULL,
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
CREATE TABLE Cousrses (
    --学生兴趣表
    Courses_id INT PRIMARY KEY,
    major_id INT NOT NULL,
    department_id INT NOT NULL,
    Cousre_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (major_id) REFERENCES majors(major_id)
);


--插入数据
-- 插入数据到 universities 表
INSERT INTO universities (university_id, name, location) VALUES
(1, 'Peking University', 'Beijing'),
(2, 'Tsinghua University', 'Beijing'),
(3, 'Fudan University', 'Shanghai'),
(4, 'Zhejiang University', 'Hangzhou'),
(5, 'Shanghai Jiao Tong University', 'Shanghai'),
(6, 'University of Science and Technology of China', 'Hefei'),
(7, 'Nanjing University', 'Nanjing'),
(8, 'Wuhan University', 'Wuhan'),
(9, 'Sun Yat-sen University', 'Guangzhou'),
(10, 'Harbin Institute of Technology', 'Harbin');

-- 插入数据到 departments 表
INSERT INTO departments (department_id, university_id, name) VALUES
(1, 1, 'Computer Science'),
(2, 1, 'Physics'),
(3, 2, 'Computer Science'),
(4, 2, 'Mathematics'),
(5, 3, 'Computer Science'),
(6, 3, 'Chemistry'),
(7, 4, 'Electrical Engineering'),
(8, 4, 'Mechanical Engineering'),
(9, 5, 'Computer Science'),
(10, 5, 'Business Administration');

-- 插入数据到 majors 表
INSERT INTO majors (major_id, department_id, name) VALUES
(1, 1, 'Software Engineering'),
(2, 1, 'Data Science'),
(3, 2, 'Theoretical Physics'),
(4, 3, 'Artificial Intelligence'),
(5, 4, 'Applied Mathematics'),
(6, 5, 'Information Technology'),
(7, 6, 'Organic Chemistry'),
(8, 7, 'Power Systems Engineering'),
(9, 8, 'Robotics'),
(10, 9, 'Network Security');

-- 插入数据到 classes 表
INSERT INTO classes (class_id, class_name, major_id) VALUES
(1, 'SE2024', 1),
(2, 'DS2024', 2),
(3, 'TP2024', 3),
(4, 'AI2024', 4),
(5, 'AM2024', 5),
(6, 'IT2024', 6),
(7, 'OC2024', 7),
(8, 'PSE2024', 8),
(9, 'R2024', 9),
(10, 'NS2024', 10);

-- 插入数据到 students 表
INSERT INTO students (student_id, name, gender, age, score, class_id) VALUES
(1, 'Alice', 'Female', 20, 90, 1),
(2, 'Bob', 'Male', 21, 85, 2),
(3, 'Charlie', 'Male', 22, 88, 3),
(4, 'David', 'Male', 21, 92, 4),
(5, 'Eve', 'Female', 22, 87, 5),
(6, 'Frank', 'Male', 23, 89, 6),
(7, 'Grace', 'Female', 20, 91, 7),
(8, 'Heidi', 'Female', 22, 93, 8),
(9, 'Ivan', 'Male', 21, 84, 9),
(10, 'Judy', 'Female', 23, 86, 10);

-- 插入数据到 applications 表
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(1, 1, 1, 1, 1, FALSE),
(2, 2, 2, 3, 4, FALSE),
(3, 3, 1, 2, 3, FALSE),
(4, 4, 2, 4, 5, TRUE),
(5, 5, 5, 10, 10, FALSE),
(6, 6, 3, 6, 7, TRUE),
(7, 7, 4, 7, 8, FALSE),
(8, 8, 4, 8, 9, FALSE),
(9, 9, 5, 9, 10, TRUE),
(10, 10, 1, 1, 2, FALSE),
(11, 1, 3, 6, 7, FALSE),
(12, 1, 3, 6, 7, TRUE),
(13, 1, 4, 7, 8, FALSE),
(14, 1, 4, 7, 8, TRUE),
(15, 1, 4, 8, 9, FALSE),
(16, 1, 4, 8, 9, TRUE),
(17, 1, 5, 9, 10, FALSE),
(18, 1, 5, 9, 10, TRUE),
(19, 1, 5, 10, 10, FALSE),
(20, 1, 5, 10, 10, TRUE),
(21, 1, 6, 6, 7, FALSE),
(22, 1, 6, 6, 7, TRUE),
(23, 1, 7, 7, 8, FALSE),
(24, 1, 7, 7, 8, TRUE),
(25, 1, 8, 8, 9, FALSE),
(26, 1, 8, 8, 9, TRUE),
(27, 1, 9, 9, 10, FALSE),
(28, 1, 9, 9, 10, TRUE),
(29, 1, 10, 10, 10, FALSE),
(30, 1, 10, 10, 10, TRUE),

-- 为学生 2 生成 30 条 applications 数据
(31, 2, 1, 1, 1, FALSE),
(32, 2, 1, 1, 2, FALSE),
(33, 2, 1, 2, 3, FALSE),
(34, 2, 1, 2, 3, TRUE),
(35, 2, 2, 3, 4, FALSE),
(36, 2, 2, 3, 4, TRUE),
(37, 2, 2, 4, 5, FALSE),
(38, 2, 2, 4, 5, TRUE),
(39, 2, 3, 5, 6, FALSE),
(40, 2, 3, 5, 6, TRUE),
(41, 2, 3, 6, 7, FALSE),
(42, 2, 3, 6, 7, TRUE),
(43, 2, 4, 7, 8, FALSE),
(44, 2, 4, 7, 8, TRUE),
(45, 2, 4, 8, 9, FALSE),
(46, 2, 4, 8, 9, TRUE),
(47, 2, 5, 9, 10, FALSE),
(48, 2, 5, 9, 10, TRUE),
(49, 2, 5, 10, 10, FALSE),
(50, 2, 5, 10, 10, TRUE),
(51, 2, 6, 6, 7, FALSE),
(52, 2, 6, 6, 7, TRUE),
(53, 2, 7, 7, 8, FALSE),
(54, 2, 7, 7, 8, TRUE),
(55, 2, 8, 8, 9, FALSE),
(56, 2, 8, 8, 9, TRUE),
(57, 2, 9, 9, 10, FALSE),
(58, 2, 9, 9, 10, TRUE),
(59, 2, 10, 10, 10, FALSE),
(60, 2, 10, 10, 10, TRUE),

-- 为学生 3 生成 30 条 applications 数据
(61, 3, 1, 1, 1, FALSE),
(62, 3, 1, 1, 2, FALSE),
(63, 3, 1, 2, 3, FALSE),
(64, 3, 1, 2, 3, TRUE),
(65, 3, 2, 3, 4, FALSE),
(66, 3, 2, 3, 4, TRUE),
(67, 3, 2, 4, 5, FALSE),
(68, 3, 2, 4, 5, TRUE),
(69, 3, 3, 5, 6, FALSE),
(70, 3, 3, 5, 6, TRUE),
(71, 3, 3, 6, 7, FALSE),
(72, 3, 3, 6, 7, TRUE),
(73, 3, 4, 7, 8, FALSE),
(74, 3, 4, 7, 8, TRUE),
(75, 3, 4, 8, 9, FALSE),
(76, 3, 4, 8, 9, TRUE),
(77, 3, 5, 9, 10, FALSE),
(78, 3, 5, 9, 10, TRUE),
(79, 3, 5, 10, 10, FALSE),
(80, 3, 5, 10, 10, TRUE),
(81, 3, 6, 6, 7, FALSE),
(82, 3, 6, 6, 7, TRUE),
(83, 3, 7, 7, 8, FALSE),
(84, 3, 7, 7, 8, TRUE),
(85, 3, 8, 8, 9, FALSE),
(86, 3, 8, 8, 9, TRUE),
(87, 3, 9, 9, 10, FALSE),
(88, 3, 9, 9, 10, TRUE),
(89, 3, 10, 10, 10, FALSE),
(90, 3, 10, 10, 10, TRUE),

-- 为学生 4 生成 30 条 applications 数据
(91, 4, 1, 1, 1, FALSE),
(92, 4, 1, 1, 2, FALSE),
(93, 4, 1, 2, 3, FALSE),
(94, 4, 1, 2, 3, TRUE),
(95, 4, 2, 3, 4, FALSE),
(96, 4, 2, 3, 4, TRUE),
(97, 4, 2, 4, 5, FALSE),
(98, 4, 2, 4, 5, TRUE),
(99, 4, 3, 5, 6, FALSE),
(100, 4, 3, 5, 6, TRUE),
(101, 4, 3, 6, 7, FALSE),
(102, 4, 3, 6, 7, TRUE),
(103, 4, 4, 7, 8, FALSE),
(104, 4, 4, 7, 8, TRUE),
(105, 4, 4, 8, 9, FALSE),
(106, 4, 4, 8, 9, TRUE),
(107, 4, 5, 9, 10, FALSE),
(108, 4, 5, 9, 10, TRUE),
(109, 4, 5, 10, 10, FALSE),
(110, 4, 5, 10, 10, TRUE),
(111, 4, 6, 6, 7, FALSE),
(112, 4, 6, 6, 7, TRUE),
(113, 4, 7, 7, 8, FALSE),
(114, 4, 7, 7, 8, TRUE),
(115, 4, 8, 8, 9, FALSE),
(116, 4, 8, 8, 9, TRUE),
(117, 4, 9, 9, 10, FALSE),
(118, 4, 9, 9, 10, TRUE),
(119, 4, 10, 10, 10, FALSE),
(120, 4, 10, 10, 10, TRUE),

-- 为学生 5 生成 30 条 applications 数据
(121, 5, 1, 1, 1, FALSE),
(122, 5, 1, 1, 2, FALSE),
(123, 5, 1, 2, 3, FALSE),
(124, 5, 1, 2, 3, TRUE),
(125, 5, 2, 3, 4, FALSE),
(126, 5, 2, 3, 4, TRUE),
(127, 5, 2, 4, 5, FALSE),
(128, 5, 2, 4, 5, TRUE),
(129, 5, 3, 5, 6, FALSE),
(130, 5, 3, 5, 6, TRUE),
(131, 5, 3, 6, 7, FALSE),
(132, 5, 3, 6, 7, TRUE),
(133, 5, 4, 7, 8, FALSE),
(134, 5, 4, 7, 8, TRUE),
(135, 5, 4, 8, 9, FALSE),
(136, 5, 4, 8, 9, TRUE),
(137, 5, 5, 9, 10, FALSE),
(138, 5, 5, 9, 10, TRUE),
(139, 5, 5, 10, 10, FALSE),
(140, 5, 5, 10, 10, TRUE),
(141, 5, 6, 6, 7, FALSE),
(142, 5, 6, 6, 7, TRUE),
(143, 5, 7, 7, 8, FALSE),
(144, 5, 7, 7, 8, TRUE),
(145, 5, 8, 8, 9, FALSE),
(146, 5, 8, 8, 9, TRUE),
(147, 5, 9, 9, 10, FALSE),
(148, 5, 9, 9, 10, TRUE),
(149, 5, 10, 10, 10, FALSE),
(150, 5, 10, 10, 10, TRUE),

-- 为学生 6 生成 30 条 applications 数据
(151, 6, 1, 1, 1, FALSE),
(152, 6, 1, 1, 2, FALSE),
(153, 6, 1, 2, 3, FALSE),
(154, 6, 1, 2, 3, TRUE),
(155, 6, 2, 3, 4, FALSE),
(156, 6, 2, 3, 4, TRUE),
(157, 6, 2, 4, 5, FALSE),
(158, 6, 2, 4, 5, TRUE),
(159, 6, 3, 5, 6, FALSE),
(160, 6, 3, 5, 6, TRUE),
(161, 6, 3, 6, 7, FALSE),
(162, 6, 3, 6, 7, TRUE),
(163, 6, 4, 7, 8, FALSE),
(164, 6, 4, 7, 8, TRUE),
(165, 6, 4, 8, 9, FALSE),
(166, 6, 4, 8, 9, TRUE),
(167, 6, 5, 9, 10, FALSE),
(168, 6, 5, 9, 10, TRUE),
(169, 6, 5, 10, 10, FALSE),
(170, 6, 5, 10, 10, TRUE),
(171, 6, 6, 6, 7, FALSE),
(172, 6, 6, 6, 7, TRUE),
(173, 6, 7, 7, 8, FALSE),
(174, 6, 7, 7, 8, TRUE),
(175, 6, 8, 8, 9, FALSE),
(176, 6, 8, 8, 9, TRUE),
(177, 6, 9, 9, 10, FALSE),
(178, 6, 9, 9, 10, TRUE),
(179, 6, 10, 10, 10, FALSE),
(180, 6, 10, 10, 10, TRUE),

-- 为学生 7 生成 30 条 applications 数据
(181, 7, 1, 1, 1, FALSE),
(182, 7, 1, 1, 2, FALSE),
(183, 7, 1, 2, 3, FALSE),
(184, 7, 1, 2, 3, TRUE),
(185, 7, 2, 3, 4, FALSE),
(186, 7, 2, 3, 4, TRUE),
(187, 7, 2, 4, 5, FALSE),
(188, 7, 2, 4, 5, TRUE),
(189, 7, 3, 5, 6, FALSE),
(190, 7, 3, 5, 6, TRUE),
(191, 7, 3, 6, 7, FALSE),
(192, 7, 3, 6, 7, TRUE),
(193, 7, 4, 7, 8, FALSE),
(194, 7, 4, 7, 8, TRUE),
(195, 7, 4, 8, 9, FALSE),
(196, 7, 4, 8, 9, TRUE),
(197, 7, 5, 9, 10, FALSE),
(198, 7, 5, 9, 10, TRUE),
(199, 7, 5, 10, 10, FALSE),
(200, 7, 5, 10, 10, TRUE),
(201, 7, 6, 6, 7, FALSE),
(202, 7, 6, 6, 7, TRUE),
(203, 7, 7, 7, 8, FALSE),
(204, 7, 7, 7, 8, TRUE),
(205, 7, 8, 8, 9, FALSE),
(206, 7, 8, 8, 9, TRUE),
(207, 7, 9, 9, 10, FALSE),
(208, 7, 9, 9, 10, TRUE),
(209, 7, 10, 10, 10, FALSE),
(210, 7, 10, 10, 10, TRUE),

-- 为学生 8 生成 30 条 applications 数据
(211, 8, 1, 1, 1, FALSE),
(212, 8, 1, 1, 2, FALSE),
(213, 8, 1, 2, 3, FALSE),
(214, 8, 1, 2, 3, TRUE),
(215, 8, 2, 3, 4, FALSE),
(216, 8, 2, 3, 4, TRUE),
(217, 8, 2, 4, 5, FALSE),
(218, 8, 2, 4, 5, TRUE),
(219, 8, 3, 5, 6, FALSE),
(220, 8, 3, 5, 6, TRUE),
(221, 8, 3, 6, 7, FALSE),
(222, 8, 3, 6, 7, TRUE),
(223, 8, 4, 7, 8, FALSE),
(224, 8, 4, 7, 8, TRUE),
(225, 8, 4, 8, 9, FALSE),
(226, 8, 4, 8, 9, TRUE),
(227, 8, 5, 9, 10, FALSE),
(228, 8, 5, 9, 10, FALSE),
(229, 8, 5, 10, 10, TRUE),
(230, 8, 6, 6, 7, FALSE),
(231, 8, 6, 6, 7, TRUE),
(232, 8, 7, 7, 8, FALSE),
(233, 8, 7, 7, 8, TRUE),
(234, 8, 8, 8, 9, FALSE),
(235, 8, 8, 8, 9, TRUE),
(236, 8, 9, 9, 10, FALSE),
(237, 8, 9, 9, 10, TRUE),
(238, 8, 10, 10, 10, FALSE),
(239, 8, 10, 10, 10, TRUE),

-- 为学生 9 生成 30 条 applications 数据
(240, 9, 1, 1, 1, FALSE),
(241, 9, 1, 1, 2, FALSE),
(242, 9, 1, 2, 3, FALSE),
(243, 9, 1, 2, 3, TRUE),
(244, 9, 2, 3, 4, FALSE),
(245, 9, 2, 3, 4, TRUE),
(246, 9, 2, 4, 5, FALSE),
(247, 9, 2, 4, 5, TRUE),
(248, 9, 3, 5, 6, FALSE),
(249, 9, 3, 5, 6, TRUE),
(250, 9, 3, 6, 7, FALSE),
(251, 9, 3, 6, 7, TRUE),
(252, 9, 4, 7, 8, FALSE),
(253, 9, 4, 7, 8, TRUE),
(254, 9, 4, 8, 9, FALSE),
(255, 9, 4, 8, 9, TRUE),
(256, 9, 5, 9, 10, FALSE),
(257, 9, 5, 9, 10, TRUE),
(258, 9, 5, 10, 10, FALSE),
(259, 9, 5, 10, 10, TRUE),
(260, 9, 6, 6, 7, FALSE),
(261, 9, 6, 6, 7, TRUE),
(262, 9, 7, 7, 8, FALSE),
(263, 9, 7, 7, 8, TRUE),
(264, 9, 8, 8, 9, FALSE),
(265, 9, 8, 8, 9, TRUE),
(266, 9, 9, 9, 10, FALSE),
(267, 9, 9, 9, 10, TRUE),
(268, 9, 10, 10, 10, FALSE),
(269, 9, 10, 10, 10, TRUE),

-- 为学生 10 生成 30 条 applications 数据
(270, 10, 1, 1, 1, FALSE),
(271, 10, 1, 1, 2, FALSE),
(272, 10, 1, 2, 3, FALSE),
(273, 10, 1, 2, 3, TRUE),
(274, 10, 2, 3, 4, FALSE),
(275, 10, 2, 3, 4, TRUE),
(276, 10, 2, 4, 5, FALSE),
(277, 10, 2, 4, 5, TRUE),
(278, 10, 3, 5, 6, FALSE),
(279, 10, 3, 5, 6, TRUE),
(280, 10, 3, 6, 7, FALSE),
(281, 10, 3, 6, 7, TRUE),
(282, 10, 4, 7, 8, FALSE),
(283, 10, 4, 7, 8, TRUE),
(284, 10, 4, 8, 9, FALSE),
(285, 10, 4, 8, 9, TRUE),
(286, 10, 5, 9, 10, FALSE),
(287, 10, 5, 9, 10, TRUE),
(288, 10, 5, 10, 10, FALSE),
(289, 10, 5, 10, 10, TRUE),
(290, 10, 6, 6, 7, FALSE),
(291, 10, 6, 6, 7, TRUE),
(292, 10, 7, 7, 8, FALSE),
(293, 10, 7, 7, 8, TRUE),
(294, 10, 8, 8, 9, FALSE),
(295, 10, 8, 8, 9, TRUE),
(296, 10, 9, 9, 10, FALSE),
(297, 10, 9, 9, 10, TRUE),
(298, 10, 10, 10, 10, FALSE),
(299, 10, 10, 10, 10, TRUE);

-- 插入数据到 admissions 表
INSERT INTO admissions (admission_id, student_id, university_id, department_id, major_id) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 3, 4),
(3, 3, 1, 2, 3),
(4, 4, 2, 4, 5),
(5, 5, 5, 10, 10),
(6, 6, 3, 6, 7),
(7, 7, 4, 7, 8),
(8, 8, 4, 8, 9),
(9, 9, 5, 9, 10),
(10, 10, 1, 1, 2);

-- 插入数据到 Cousrses 表
INSERT INTO Cousrses (Courses_id, major_id, department_id, Cousre_name) VALUES
(1, 1, 1, 'Introduction to Software Engineering'),
(2, 2, 1, 'Data Science Basics'),
(3, 3, 2, 'Quantum Mechanics'),
(4, 4, 3, 'Machine Learning'),
(5, 5, 4, 'Linear Algebra'),
(6, 6, 5, 'Network Security'),
(7, 7, 6, 'Organic Chemistry I'),
(8, 8, 7, 'Power Systems'),
(9, 9, 8, 'Introduction to Robotics'),
(10, 10, 9, 'Network Security Advanced');


--查询表格
SELECT * FROM universities ;
SELECT * FROM departments;
SELECT * FROM majors;
SELECT * FROM classes;
SELECT * FROM students;
SELECT * FROM  applications;
SELECT * FROM  admissions;
SELECT * FROM  Cousrses;
