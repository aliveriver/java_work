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
INSERT INTO departments (department_id, name) VALUES
(1, 'Institute of Economics and Management'),
(2, 'Institute of Architecture and Art'),
(3, 'Institute of Marxism'),
(4,  'Institute of foreign languages'),
(5,  'Institute of Physics'),
(6,  'Institute of Chemistry'),
(7, 'Institute of Mathmematics'),
(8, 'Institute of Physical Education and Health'),
(9, 'Institute of Environmental Science'),
(10,'Institute of Business Administration');

-- 插入数据到 majors 表
-- Institute of Economics and Management  
INSERT INTO majors (major_id, department_id, name) VALUES  
(1, 1, 'Finance'),  
(2, 1, 'Economics'),  
(3, 1, 'International Business');
  
-- Institute of Architecture and Art  
INSERT INTO majors (major_id, department_id, name) VALUES  
(4, 2, 'Architecture'),  
(5, 2, 'Interior Design'),  
(6, 2, 'Visual Arts'); 
  
-- Institute of Marxism  
INSERT INTO majors (major_id, department_id, name) VALUES  
(7, 3, 'Marxist Theory'),  
(8, 3, 'Political Economy'),  
(9, 3, 'Philosophy of Social Sciences'); 
  
-- Institute of foreign languages  
INSERT INTO majors (major_id, department_id, name) VALUES  
(10, 4, 'English Language and Literature'),  
(11, 4, 'Spanish Language and Culture'),  
(12, 4, 'Chinese Language and Literature');  
  
-- Institute of Physics  
INSERT INTO majors (major_id, department_id, name) VALUES  
(13, 5, 'Physics'),  
(14, 5, 'Astrophysics'),  
(15, 5, 'Quantum Physics');
  
-- Institute of Chemistry  
INSERT INTO majors (major_id, department_id, name) VALUES  
(16, 6, 'Chemistry'),  
(17, 6, 'Organic Chemistry'),  
(18, 6, 'Analytical Chemistry');
  
-- Institute of Mathematics  
INSERT INTO majors (major_id, department_id, name) VALUES  
(19, 7, 'Mathematics'),  
(20, 7, 'Applied Mathematics'),  
(21, 7, 'Statistics');
  
-- Institute of Physical Education and Health  
INSERT INTO majors (major_id, department_id, name) VALUES  
(22, 8, 'Physical Education'),  
(23, 8, 'Sports Science'),  
(24, 8, 'Health Education'); 
  
-- Institute of Environmental Science  
INSERT INTO majors (major_id, department_id, name) VALUES  
(25, 9, 'Environmental Science'),  
(26, 9, 'Sustainability Studies'),  
(27, 9, 'Environmental Engineering');
  

-- Institute of Business Administration  
INSERT INTO majors (major_id, department_id, name) VALUES  
(28, 10, 'Business Administration'),  
(29, 10, 'Marketing'),  
(30, 10, 'Human Resource Management');

-- 插入数据到 classes 表
INSERT INTO classes (class_id, class_name, major_id,University_id) VALUES
(1, 'F2024.01', 1,1),
(2, 'F2024.02', 1,1),
(3, 'E2024.01', 2,2),
(4, 'IB2024.01', 3,3),
(5, 'A2024.01', 4,4),
(6, 'ID2024.01', 5,5),
(7, 'VA2024.01', 6,6),
(8, 'MT2024.01', 7,7),
(9, 'PE2024.01', 8,8),
(10, 'POF2024.01', 9,9),
(11, 'ELL2024.01', 10,10);

-- 插入数据到 students 表
INSERT INTO students (student_id, name, gender, age, score, class_id) VALUES
(1, '伊雷娜', 'Female', 18, 720, 1),
(2, '李龙', 'Male', 18, 721, 2),
(3, '周树', 'Male', 19, 723, 3),
(4, '小黑', 'Male', 18, 724, 4),
(5, '林徽', 'Female', 18, 723, 5),
(6, '弗兰克', 'Male', 18, 731, 6),
(7, '空门苍', 'Female', 18, 731, 7),
(8, '缪尔赛思', 'Female', 18, 721, 8),
(9, '林凯', 'Male', 18, 721, 9),
(10, '朱迪', 'Female', 17, 723, 10);

-- 插入数据到 applications 表
-- 插入数据到 applications 表
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(1, 1, 1, 1, 1, 0), -- 伊雷娜第一志愿，已录取
(2, 1, 2, 1, 1, 0), -- 伊雷娜第二志愿
(3, 1, 3, 1, 1, 0), -- 伊雷娜第三志愿
(4, 1, 4, 1, 1, 0), -- 伊雷娜第四志愿
(5, 1, 5, 1, 1, 0), -- 伊雷娜第五志愿
(6, 1, 6, 1, 1, 0), -- 伊雷娜第六志愿
(7, 1, 7, 1, 1, 0), -- 伊雷娜第七志愿
(8, 1, 8, 1, 1, 0), -- 伊雷娜第八志愿
(9, 1, 9, 1, 1, 0), -- 伊雷娜第九志愿
(10, 1, 10, 1, 1, 0); -- 伊雷娜第十志愿

INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(11, 2, 1, 1, 1, 0), -- 李龙第一志愿，已录取
(12, 2, 2, 1, 1, 0), -- 李龙第二志愿
(13, 2, 3, 1, 1, 0), -- 李龙第三志愿
(14, 2, 4, 1, 1, 0), -- 李龙第四志愿
(15, 2, 5, 1, 1, 0), -- 李龙第五志愿
(16, 2, 6, 1, 1, 0), -- 李龙第六志愿
(17, 2, 7, 1, 1, 0), -- 李龙第七志愿
(18, 2, 8, 1, 1, 0), -- 李龙第八志愿
(19, 2, 9, 1, 1, 0), -- 李龙第九志愿
(20, 2, 10, 1, 1, 0); -- 李龙第十志愿
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(21, 3, 1, 1, 1, 0), -- 周树第一志愿，已录取
(22, 3, 2, 1, 1, 0), -- 周树第二志愿
(23, 3, 3, 1, 1, 0), -- 周树第三志愿
(24, 3, 4, 1, 1, 0), -- 周树第四志愿
(25, 3, 5, 1, 1, 0), -- 周树第五志愿
(26, 3, 6, 1, 1, 0), -- 周树第六志愿
(27, 3, 7, 1, 1, 0), -- 周树第七志愿
(28, 3, 8, 1, 1, 0), -- 周树第八志愿
(29, 3, 9, 1, 1, 0), -- 周树第九志愿
(30, 3, 10, 1, 1, 0); -- 周树第十志愿

-- 生成小黑的志愿信息
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(31, 4, 1, 1, 1, 0), -- 小黑第一志愿，已录取
(32, 4, 2, 1, 1, 0), -- 小黑第二志愿
(33, 4, 3, 1, 1, 0), -- 小黑第三志愿
(34, 4, 4, 1, 1, 0), -- 小黑第四志愿
(35, 4, 5, 1, 1, 0), -- 小黑第五志愿
(36, 4, 6, 1, 1, 0), -- 小黑第六志愿
(37, 4, 7, 1, 1, 0), -- 小黑第七志愿
(38, 4, 8, 1, 1, 0), -- 小黑第八志愿
(39, 4, 9, 1, 1, 0), -- 小黑第九志愿
(40, 4, 10, 1, 1, 0); -- 小黑第十志愿
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(41, 5, 1, 1, 1, 0), -- 林徽第一志愿，已录取
(42, 5, 2, 1, 1, 0), -- 林徽第二志愿
(43, 5, 3, 1, 1, 0), -- 林徽第三志愿
(44, 5, 4, 1, 1, 0), -- 林徽第四志愿
(45, 5, 5, 1, 1, 0), -- 林徽第五志愿
(46, 5, 6, 1, 1, 0), -- 林徽第六志愿
(47, 5, 7, 1, 1, 0), -- 林徽第七志愿
(48, 5, 8, 1, 1, 0), -- 林徽第八志愿
(49, 5, 9, 1, 1, 0), -- 林徽第九志愿
(50, 5, 10, 1, 1, 0); -- 林徽第十志愿

-- 生成弗兰克的志愿信息
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(51, 6, 1, 1, 1, 0), -- 弗兰克第一志愿，已录取
(52, 6, 2, 1, 1, 0), -- 弗兰克第二志愿
(53, 6, 3, 1, 1, 0), -- 弗兰克第三志愿
(54, 6, 4, 1, 1, 0), -- 弗兰克第四志愿
(55, 6, 5, 1, 1, 0), -- 弗兰克第五志愿
(56, 6, 6, 1, 1, 0), -- 弗兰克第六志愿
(57, 6, 7, 1, 1, 0), -- 弗兰克第七志愿
(58, 6, 8, 1, 1, 0), -- 弗兰克第八志愿
(59, 6, 9, 1, 1, 0), -- 弗兰克第九志愿
(60, 6, 10, 1, 1, 0); -- 弗兰克第十志愿

-- 生成空门苍的志愿信息
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(61, 7, 1, 1, 1, 0), -- 空门苍第一志愿，已录取
(62, 7, 2, 1, 1, 0), -- 空门苍第二志愿
(63, 7, 3, 1, 1, 0), -- 空门苍第三志愿
(64, 7, 4, 1, 1, 0), -- 空门苍第四志愿
(65, 7, 5, 1, 1, 0), -- 空门苍第五志愿
(66, 7, 6, 1, 1, 0), -- 空门苍第六志愿
(67, 7, 7, 1, 1, 0), -- 空门苍第七志愿
(68, 7, 8, 1, 1, 0), -- 空门苍第八志愿
(69, 7, 9, 1, 1, 0), -- 空门苍第九志愿
(70, 7, 10, 1, 1, 0); -- 空门苍第十志愿

-- 生成缪尔赛思的志愿信息
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(71, 8, 1, 1, 1, 0), -- 缪尔赛思第一志愿，已录取
(72, 8, 2, 1, 1, 0), -- 缪尔赛思第二志愿
(73, 8, 3, 1, 1, 0), -- 缪尔赛思第三志愿
(74, 8, 4, 1, 1, 0), -- 缪尔赛思第四志愿
(75, 8, 5, 1, 1, 0), -- 缪尔赛思第五志愿
(76, 8, 6, 1, 1, 0), -- 缪尔赛思第六志愿
(77, 8, 7, 1, 1, 0), -- 缪尔赛思第七志愿
(78, 8, 8, 1, 1, 0), -- 缪尔赛思第八志愿
(79, 8, 9, 1, 1, 0), -- 缪尔赛思第九志愿
(80, 8, 10, 1, 1, 0); -- 缪尔赛思第十志愿
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(81, 9, 1, 1, 1, 0), -- 林凯第一志愿，已录取
(82, 9, 2, 1, 1, 0), -- 林凯第二志愿
(83, 9, 3, 1, 1, 0), -- 林凯第三志愿
(84, 9, 4, 1, 1, 0), -- 林凯第四志愿
(85, 9, 5, 1, 1, 0), -- 林凯第五志愿
(86, 9, 6, 1, 1, 0), -- 林凯第六志愿
(87, 9, 7, 1, 1, 0), -- 林凯第七志愿
(88, 9, 8, 1, 1, 0), -- 林凯第八志愿
(89, 9, 9, 1, 1, 0), -- 林凯第九志愿
(90, 9, 10, 1, 1, 0); -- 林凯第十志愿

-- 生成朱迪的志愿信息
INSERT INTO applications (application_id, student_id, university_id, department_id, major_id, is_adjustment) VALUES
(91, 10, 1, 1, 1, 0), -- 朱迪第一志愿，已录取
(92, 10, 2, 1, 1, 0), -- 朱迪第二志愿
(93, 10, 3, 1, 1, 0), -- 朱迪第三志愿
(94, 10, 4, 1, 1, 0), -- 朱迪第四志愿
(95, 10, 5, 1, 1, 0), -- 朱迪第五志愿
(96, 10, 6, 1, 1, 0), -- 朱迪第六志愿
(97, 10, 7, 1, 1, 0), -- 朱迪第七志愿
(98, 10, 8, 1, 1, 0), -- 朱迪第八志愿
(99, 10, 9, 1, 1, 0), -- 朱迪第九志愿
(100, 10, 10, 1, 1, 0); -- 朱迪第十志愿

-- 插入数据到 admissions 表
INSERT INTO admissions (admission_id, student_id, university_id, department_id, major_id) VALUES
(1, 1, 1, 1, 1),
(2, 2, 1, 1, 1),
(3, 3, 2, 1, 2),
(4, 4, 3, 1, 3),
(5, 5, 4, 2, 4),
(6, 6, 5, 2, 5),
(7, 7, 6, 2, 6),
(8, 8, 7, 3, 7),
(9, 9, 8, 3, 8),
(10, 10, 9, 3, 9);

-- 插入数据到 Courses 表

INSERT INTO Courses (Courses_id, major_id, department_id, Course_name) VALUES
(1, 1, 1, 'Advanced Corporate Finance'), -- Finance
(2, 2, 1, 'Macroeconomic Theory'), -- Economics
(3, 3, 1, 'Global Business Strategy'), -- International Business
(4, 4, 2, 'Architectural Design Studio'), -- Architecture
(5, 5, 2, 'Interior Design Principles'), -- Interior Design
(6, 6, 2, 'Visual Arts Workshop'), -- Visual Arts
(7, 7, 3, 'Marxist Political Thought'), -- Marxist Theory
(8, 8, 3, 'Economic Determinism'), -- Political Economy
(9, 9, 3, 'Philosophy of Social Change'), -- Philosophy of Social Sciences
(10, 10, 4, 'Literary Analysis and Criticism'), -- English Language and Literature
(11, 11, 4, 'Spanish Conversation and Composition'), -- Spanish Language and Culture
(12, 12, 4, 'Classical Chinese Literature'), -- Chinese Language and Literature
(13, 13, 5, 'Classical Mechanics'), -- Physics
(14, 14, 5, 'Stellar Astrophysics'), -- Astrophysics
(15, 15, 5, 'Introduction to Quantum Mechanics'), -- Quantum Physics
(16, 16, 6, 'Inorganic Chemistry'), -- Chemistry
(17, 17, 6, 'Introduction to Organic Synthesis'), -- Organic Chemistry
(18, 18, 6, 'Analytical Techniques in Chemistry'), -- Analytical Chemistry
(19, 19, 7, 'Linear Algebra'), -- Mathematics
(20, 20, 7, 'Numerical Methods in Applied Mathematics'), -- Applied Mathematics
(21, 21, 7, 'Statistical Modeling'), -- Statistics
(22, 22, 8, 'Physical Fitness and Conditioning'), -- Physical Education
(23, 23, 8, 'Sports Nutrition and Dietetics'), -- Sports Science
(24, 24, 8, 'Health Promotion and Wellness'), -- Health Education
(25, 25, 9, 'Environmental Policy and Management'), -- Environmental Science
(26, 26, 9, 'Sustainable Development Practices'), -- Sustainability Studies
(27, 27, 9, 'Environmental Impact Assessment'), -- Environmental Engineering
(28, 28, 10, 'Strategic Management'), -- Business Administration
(29, 29, 10, 'Consumer Behavior and Marketing Strategy'), -- Marketing
(30, 30, 10, 'Human Resource Planning and Development'); -- Human Resource Management


INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(1, 1, 1, 1, 720,1,3), -- Finance
(2, 1, 1, 2, 710,1,3), -- Economics
(3, 1, 1, 3, 700,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(4, 1, 2, 4, 700,1,3), -- Architecture
(5, 1, 2, 5, 690,1,3), -- Interior Design
(6, 1, 2, 6, 680,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(7, 1, 3, 7, 680,1,3), -- Marxist Theory
(8, 1, 3, 8, 670,1,3), -- Political Economy
(9, 1, 3, 9, 660,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(10, 1, 4, 10, 690,1,3), -- English Language and Literature
(11, 1, 4, 11, 680,1,3), -- Spanish Language and Culture
(12, 1, 4, 12, 670,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(13, 1, 5, 13, 700,1,3), -- Physics
(14, 1, 5, 14, 690,1,3), -- Astrophysics
(15, 1, 5, 15, 680,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(16, 1, 6, 16, 690,1,3), -- Chemistry
(17, 1, 6, 17, 680,1,3), -- Organic Chemistry
(18, 1, 6, 18, 670,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(19, 1, 7, 19, 680,1,3), -- Mathematics
(20, 1, 7, 20, 670,1,3), -- Applied Mathematics
(21, 1, 7, 21, 660,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(22, 1, 8, 22, 660,1,3), -- Physical Education
(23, 1, 8, 23, 650,1,3), -- Sports Science
(24, 1, 8, 24, 640,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(25, 1, 9, 25, 670,1,3), -- Environmental Science
(26, 1, 9, 26, 660,1,3), -- Sustainability Studies
(27, 1, 9, 27, 650,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(28, 1, 10, 28, 700,1,3), -- Business Administration
(29, 1, 10, 29, 690,1,3), -- Marketing
(30, 1, 10, 30, 680,1,3); -- Human Resource Management

-- Tsinghua University
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(31, 2, 1, 1, 730,1,3), -- Finance
(32, 2, 1, 2, 720,1,3), -- Economics
(33, 2, 1, 3, 710,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(34, 2, 2, 4, 710,1,3), -- Architecture
(35, 2, 2, 5, 700,1,3), -- Interior Design
(36, 2, 2, 6, 690,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(37, 2, 3, 7, 690,1,3), -- Marxist Theory
(38, 2, 3, 8, 680,1,3), -- Political Economy
(39, 2, 3, 9, 670,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(40, 2, 4, 10, 700,1,3), -- English Language and Literature
(41, 2, 4, 11, 690,1,3), -- Spanish Language and Culture
(42, 2, 4, 12, 680,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(43, 2, 5, 13, 710,1,3), -- Physics
(44, 2, 5, 14, 700,1,3), -- Astrophysics
(45, 2, 5, 15, 690,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(46, 2, 6, 16, 700,1,3), -- Chemistry
(47, 2, 6, 17, 690,1,3), -- Organic Chemistry
(48, 2, 6, 18, 680,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(49, 2, 7, 19, 690,1,3), -- Mathematics
(50, 2, 7, 20, 680,1,3), -- Applied Mathematics
(51, 2, 7, 21, 670,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(52, 2, 8, 22, 670,1,3), -- Physical Education
(53, 2, 8, 23, 660,1,3), -- Sports Science
(54, 2, 8, 24, 650,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(55, 2, 9, 25, 680,1,3), -- Environmental Science
(56, 2, 9, 26, 670,1,3), -- Sustainability Studies
(57, 2, 9, 27, 660,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(58, 2, 10, 28, 710,1,3), -- Business Administration
(59, 2, 10, 29, 700,1,3), -- Marketing
(60, 2, 10, 30, 690,1,3); -- Human Resource Management
-- Fudan University
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(61, 3, 1, 1, 710,1,3), -- Finance
(62, 3, 1, 2, 700,1,3), -- Economics
(63, 3, 1, 3, 690,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(64, 3, 2, 4, 700,1,3), -- Architecture
(65, 3, 2, 5, 690,1,3), -- Interior Design
(66, 3, 2, 6, 680,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(67, 3, 3, 7, 680,1,3), -- Marxist Theory
(68, 3, 3, 8, 670,1,3), -- Political Economy
(69, 3, 3, 9, 660,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(70, 3, 4, 10, 690,1,3), -- English Language and Literature
(71, 3, 4, 11, 680,1,3), -- Spanish Language and Culture
(72, 3, 4, 12, 670,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(73, 3, 5, 13, 700,1,3), -- Physics
(74, 3, 5, 14, 690,1,3), -- Astrophysics
(75, 3, 5, 15, 680,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(76, 3, 6, 16, 690,1,3), -- Chemistry
(77, 3, 6, 17, 680,1,3), -- Organic Chemistry
(78, 3, 6, 18, 670,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(79, 3, 7, 19, 680,1,3), -- Mathematics
(80, 3, 7, 20, 670,1,3), -- Applied Mathematics
(81, 3, 7, 21, 660,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(82, 3, 8, 22, 660,1,3), -- Physical Education
(83, 3, 8, 23, 650,1,3), -- Sports Science
(84, 3, 8, 24, 640,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(85, 3, 9, 25, 670,1,3), -- Environmental Science
(86, 3, 9, 26, 660,1,3), -- Sustainability Studies
(87, 3, 9, 27, 650,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(88, 3, 10, 28, 700,1,3), -- Business Administration
(89, 3, 10, 29, 690,1,3), -- Marketing
(90, 3, 10, 30, 680,1,3); -- Human Resource Management
-- Zhejiang University
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(91, 4, 1, 1, 700,1,3), -- Finance
(92, 4, 1, 2, 690,1,3), -- Economics
(93, 4, 1, 3, 680,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(94, 4, 2, 4, 690,1,3), -- Architecture
(95, 4, 2, 5, 680,1,3), -- Interior Design
(96, 4, 2, 6, 670,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(97, 4, 3, 7, 670,1,3), -- Marxist Theory
(98, 4, 3, 8, 660,1,3), -- Political Economy
(99, 4, 3, 9, 650,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(100, 4, 4, 10, 680,1,3), -- English Language and Literature
(101, 4, 4, 11, 670,1,3), -- Spanish Language and Culture
(102, 4, 4, 12, 660,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(103, 4, 5, 13, 690,1,3), -- Physics
(104, 4, 5, 14, 680,1,3), -- Astrophysics
(105, 4, 5, 15, 670,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(106, 4, 6, 16, 680,1,3), -- Chemistry
(107, 4, 6, 17, 670,1,3), -- Organic Chemistry
(108, 4, 6, 18, 660,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(109, 4, 7, 19, 670,1,3), -- Mathematics
(110, 4, 7, 20, 660,1,3), -- Applied Mathematics
(111, 4, 7, 21, 650,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(112, 4, 8, 22, 660,1,3), -- Physical Education
(113, 4, 8, 23, 650,1,3), -- Sports Science
(114, 4, 8, 24, 640,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(115, 4, 9, 25, 670,1,3), -- Environmental Science
(116, 4, 9, 26, 660,1,3), -- Sustainability Studies
(117, 4, 9, 27, 650,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(118, 4, 10, 28, 690,1,3), -- Business Administration
(119, 4, 10, 29, 680,1,3), -- Marketing
(120, 4, 10, 30, 670,1,3); -- Human Resource Management
-- Shanghai Jiao Tong University
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(121, 5, 1, 1, 710,1,3), -- Finance
(122, 5, 1, 2, 700,1,3), -- Economics
(123, 5, 1, 3, 690,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(124, 5, 2, 4, 700,1,3), -- Architecture
(125, 5, 2, 5, 690,1,3), -- Interior Design
(126, 5, 2, 6, 680,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(127, 5, 3, 7, 680,1,3), -- Marxist Theory
(128, 5, 3, 8, 670,1,3), -- Political Economy
(129, 5, 3, 9, 660,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(130, 5, 4, 10, 690,1,3), -- English Language and Literature
(131, 5, 4, 11, 680,1,3), -- Spanish Language and Culture
(132, 5, 4, 12, 670,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(133, 5, 5, 13, 700,1,3), -- Physics
(134, 5, 5, 14, 690,1,3), -- Astrophysics
(135, 5, 5, 15, 680,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(136, 5, 6, 16, 690,1,3), -- Chemistry
(137, 5, 6, 17, 680,1,3), -- Organic Chemistry
(138, 5, 6, 18, 670,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(139, 5, 7, 19, 680,1,3), -- Mathematics
(140, 5, 7, 20, 670,1,3), -- Applied Mathematics
(141, 5, 7, 21, 660,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(142, 5, 8, 22, 660,1,3), -- Physical Education
(143, 5, 8, 23, 650,1,3), -- Sports Science
(144, 5, 8, 24, 640,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(145, 5, 9, 25, 670,1,3), -- Environmental Science
(146, 5, 9, 26, 660,1,3), -- Sustainability Studies
(147, 5, 9, 27, 650,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(148, 5, 10, 28, 700,1,3), -- Business Administration
(149, 5, 10, 29, 690,1,3), -- Marketing
(150, 5, 10, 30, 680,1,3); -- Human Resource Management

-- University of Science and Technology of China (中国科学技术大学) //可能会有问题
-- Institute of Economics and Management
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(151, 6, 1, 1, 685,1,3), -- Finance
(152, 6, 1, 2, 695,1,3), -- Economics
(153, 6, 1, 3, 675,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(154, 6, 2, 4, 700,1,3), -- Architecture
(155, 6, 2, 5, 685,1,3), -- Interior Design
(156, 6, 2, 6, 690,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(157, 6, 3, 7, 700,1,3), -- Marxist Theory
(158, 6, 3, 8, 680,1,3), -- Political Economy
(159, 6, 3, 9, 690,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(160, 6, 4, 10, 690,1,3), -- English Language and Literature
(161, 6, 4, 11, 685,1,3), -- Spanish Language and Culture
(162, 6, 4, 12, 670,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(163, 6, 5, 13, 695,1,3), -- Physics
(164, 6, 5, 14, 675,1,3), -- Astrophysics
(165, 6, 5, 15, 685,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(166, 6, 6, 16, 670,1,3), -- Chemistry
(167, 6, 6, 17, 700,1,3), -- Organic Chemistry
(168, 6, 6, 18, 680,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(169, 6, 7, 19, 690,1,3), -- Mathematics
(170, 6, 7, 20, 675,1,3), -- Applied Mathematics
(171, 6, 7, 21, 680,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(172, 6, 8, 22, 685,1,3), -- Physical Education
(173, 6, 8, 23, 670,1,3), -- Sports Science
(174, 6, 8, 24, 690,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(175, 6, 9, 25, 695,1,3), -- Environmental Science
(176, 6, 9, 26, 675,1,3), -- Sustainability Studies
(177, 6, 9, 27, 670,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(178, 6, 10, 28, 700,1,3), -- Business Administration
(179, 6, 10, 29, 680,1,3), -- Marketing
(180, 6, 10, 30, 695,1,3); -- Human Resource Management


--南京大学
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(181, 7, 1, 1, 690,1,3), -- Finance
(182, 7, 1, 2, 685,1,3), -- Economics
(183, 7, 1, 3, 675,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(184, 7, 2, 4, 695,1,3), -- Architecture
(185, 7, 2, 5, 680,1,3), -- Interior Design
(186, 7, 2, 6, 670,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(187, 7, 3, 7, 690,1,3), -- Marxist Theory
(188, 7, 3, 8, 680,1,3), -- Political Economy
(189, 7, 3, 9, 670,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(190, 7, 4, 10, 695,1,3), -- English Language and Literature
(191, 7, 4, 11, 685,1,3), -- Spanish Language and Culture
(192, 7, 4, 12, 675,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(193, 7, 5, 13, 700,1,3), -- Physics
(194, 7, 5, 14, 690,1,3), -- Astrophysics
(195, 7, 5, 15, 680,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(196, 7, 6, 16, 680,1,3), -- Chemistry
(197, 7, 6, 17, 675,1,3), -- Organic Chemistry
(198, 7, 6, 18, 670,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(199, 7, 7, 19, 695,1,3), -- Mathematics
(200, 7, 7, 20, 685,1,3), -- Applied Mathematics
(201, 7, 7, 21, 670,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(202, 7, 8, 22, 675,1,3), -- Physical Education
(203, 7, 8, 23, 670,1,3), -- Sports Science
(204, 7, 8, 24, 660,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(205, 7, 9, 25, 690,1,3), -- Environmental Science
(206, 7, 9, 26, 680,1,3), -- Sustainability Studies
(207, 7, 9, 27, 670,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(208, 7, 10, 28, 700,1,3), -- Business Administration
(209, 7, 10, 29, 690,1,3), -- Marketing
(210, 7, 10, 30, 680,1,3); -- Human Resource Management


--武汉大学
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(211, 8, 1, 1, 665,1,3), -- Finance
(212, 8, 1, 2, 670,1,3), -- Economics
(213, 8, 1, 3, 660,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(214, 8, 2, 4, 675,1,3), -- Architecture
(215, 8, 2, 5, 655,1,3), -- Interior Design
(216, 8, 2, 6, 640,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(217, 8, 3, 7, 670,1,3), -- Marxist Theory
(218, 8, 3, 8, 665,1,3), -- Political Economy
(219, 8, 3, 9, 640,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(220, 8, 4, 10, 660,1,3), -- English Language and Literature
(221, 8, 4, 11, 645,1,3), -- Spanish Language and Culture
(222, 8, 4, 12, 635,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(223, 8, 5, 13, 680,1,3), -- Physics
(224, 8, 5, 14, 670,1,3), -- Astrophysics
(225, 8, 5, 15, 660,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(226, 8, 6, 16, 665,1,3), -- Chemistry
(227, 8, 6, 17, 655,1,3), -- Organic Chemistry
(228, 8, 6, 18, 640,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(229, 8, 7, 19, 670,1,3), -- Mathematics
(230, 8, 7, 20, 660,1,3), -- Applied Mathematics
(231, 8, 7, 21, 645,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(232, 8, 8, 22, 655,1,3), -- Physical Education
(233, 8, 8, 23, 645,1,3), -- Sports Science
(234, 8, 8, 24, 635,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(235, 8, 9, 25, 660,1,3), -- Environmental Science
(236, 8, 9, 26, 650,1,3), -- Sustainability Studies
(237, 8, 9, 27, 640,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(238, 8, 10, 28, 675,1,3), -- Business Administration
(239, 8, 10, 29, 665,1,3), -- Marketing
(240, 8, 10, 30, 650,1,3); -- Human Resource Management
--中山大学
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(241, 9, 1, 1, 630,1,3), -- Finance
(242, 9, 1, 2, 640,1,3), -- Economics
(243, 9, 1, 3, 620,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(244, 9, 2, 4, 625,1,3), -- Architecture
(245, 9, 2, 5, 635,1,3), -- Interior Design
(246, 9, 2, 6, 630,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(247, 9, 3, 7, 640,1,3), -- Marxist Theory
(248, 9, 3, 8, 630,1,3), -- Political Economy
(249, 9, 3, 9, 615,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(250, 9, 4, 10, 620,1,3), -- English Language and Literature
(251, 9, 4, 11, 630,1,3), -- Spanish Language and Culture
(252, 9, 4, 12, 615,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(253, 9, 5, 13, 640,1,3), -- Physics
(254, 9, 5, 14, 635,1,3), -- Astrophysics
(255, 9, 5, 15, 620,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(256, 9, 6, 16, 630,1,3), -- Chemistry
(257, 9, 6, 17, 625,1,3), -- Organic Chemistry
(258, 9, 6, 18, 615,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(259, 9, 7, 19, 635,1,3), -- Mathematics
(260, 9, 7, 20, 625,1,3), -- Applied Mathematics
(261, 9, 7, 21, 610,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(262, 9, 8, 22, 620,1,3), -- Physical Education
(263, 9, 8, 23, 615,1,3), -- Sports Science
(264, 9, 8, 24, 610,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(265, 9, 9, 25, 625,1,3), -- Environmental Science
(266, 9, 9, 26, 620,1,3), -- Sustainability Studies
(267, 9, 9, 27, 615,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(268, 9, 10, 28, 640,1,3), -- Business Administration
(269, 9, 10, 29, 630,1,3), -- Marketing
(270, 9, 10, 30, 620,1,3); -- Human Resource Management
--哈尔滨工业大学
-- Institute of Economics and Management
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(271, 10, 1, 1, 690,1,3), -- Finance
(272, 10, 1, 2, 670,1,3), -- Economics
(273, 10, 1, 3, 680,1,3); -- International Business

-- Institute of Architecture and Art
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(274, 10, 2, 4, 675,1,3), -- Architecture
(275, 10, 2, 5, 685,1,3), -- Interior Design
(276, 10, 2, 6, 665,1,3); -- Visual Arts

-- Institute of Marxism
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(277, 10, 3, 7, 695,1,3), -- Marxist Theory
(278, 10, 3, 8, 685,1,3), -- Political Economy
(279, 10, 3, 9, 670,1,3); -- Philosophy of Social Sciences

-- Institute of foreign languages
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(280, 10, 4, 10, 685,1,3), -- English Language and Literature
(281, 10, 4, 11, 675,1,3), -- Spanish Language and Culture
(282, 10, 4, 12, 690,1,3); -- Chinese Language and Literature

-- Institute of Physics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(283, 10, 5, 13, 695,1,3), -- Physics
(284, 10, 5, 14, 685,1,3), -- Astrophysics
(285, 10, 5, 15, 675,1,3); -- Quantum Physics

-- Institute of Chemistry
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(286, 10, 6, 16, 665,1,3), -- Chemistry
(287, 10, 6, 17, 675,1,3), -- Organic Chemistry
(288, 10, 6, 18, 670,1,3); -- Analytical Chemistry

-- Institute of Mathematics
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(289, 10, 7, 19, 690,1,3), -- Mathematics
(290, 10, 7, 20, 680,1,3), -- Applied Mathematics
(291, 10, 7, 21, 665,1,3); -- Statistics

-- Institute of Physical Education and Health
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(292, 10, 8, 22, 675,1,3), -- Physical Education
(293, 10, 8, 23, 670,1,3), -- Sports Science
(294, 10, 8, 24, 660,1,3); -- Health Education

-- Institute of Environmental Science
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(295, 10, 9, 25, 685,1,3), -- Environmental Science
(296, 10, 9, 26, 675,1,3), -- Sustainability Studies
(297, 10, 9, 27, 665,1,3); -- Environmental Engineering

-- Institute of Business Administration
INSERT INTO EnrollmentMark (Enrollment_id, university_id, department_id, major_id, RequiredScore,MRequiredN,DRequiredN) VALUES
(298, 10, 10, 28, 695,1,3), -- Business Administration
(299, 10, 10, 29, 685,1,3), -- Marketing
(300, 10, 10, 30, 670,1,3); -- Human Resource Management
