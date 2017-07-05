--SQL queries after tuning and after adding primary and foreign keys
CREATE DATABASE Project3;
USE Project3;
--id is the primary key for Student
--id is the primary key for Professor
--crsCode is the primary key for Course
--crsCode+semester+profId is the primary key for Teaching
--studId+crsCode is the primary key for Transcript
--crsCode is the foreign key for Transcript to Course(crsCode)
--crsCode is the foreign key for Teaching to Course(crsCode)
--profId is the foreign key for Teaching to Professor(id)
--studId is the foreign key for Transcript to Student(id)

--1.List the name of the student with id equal to v1 (id).
--Takes 0.00023 to 0.00034 sec to execute and this was from just adding id as the 
--primary key which creates an index for id for Student
SELECT name FROM Student WHERE id = 995293;

--2.List the names of students with id in the range of v2 (id) to v3 (inclusive).
--Takes 0.00029 to 0.00035 sec to execute and this was from just adding id as the primary 
--key which creates an index for id for Student
SELECT name FROM Student WHERE id BETWEEN 131 AND 5379;

--3.List the names of students who have taken course v4 (crsCode).
--CREATE INDEX studName ON Student(name);
--CREATE INDEX courseName ON Course(crsName);
--CREATE INDEX profName ON Professor(name);
--Takes 0.00035 to 0.00041 sec to execute and this was just from adding id as the primary 
--key which creates and index for id for Student
SELECT name FROM student INNER JOIN (SELECT studId FROM Transcript WHERE crsCode = 
'crsCode579449') AS t ON id = t.studId;

--4.List the names of students who have taken a course taught by professor v5 (name).
--Takes 0.00045 to 0.00057 sec to execute from adding the primary key id for Student,
--primary key id for Professor
SELECT name FROM Student INNER JOIN (SELECT studId FROM Transcript p  INNER JOIN 
(select crsCode FROM Teaching WHERE profId = (SELECT id FROM Professor WHERE name = 
'name260723')) AS r ON p.crsCode = r.crsCode ) AS t ON id =t.studId;

--5.List the names of students who have taken a course from department v6 (deptId), 
--but not v7.
--Takes 0.0016 to 0.0024 sec to execute just from adding primary and foreign keys 
--for the respective tables
SELECT name FROM  student WHERE id IN (SELECT studId FROM transcript WHERE crsCode IN 
(SELECT crsCode FROM course WHERE deptId = 'deptId50411')) AND id not IN  
((SELECT studId FROM transcript WHERE crsCode IN (SELECT crsCode FROM course WHERE 
deptId = 'deptId563888')));

--6.List the names of students who have taken all courses offered by department v8 
--(deptId).
--Takes 0.00093 to 0.00096 sec to execute just from adding primary keys and foreign 
--keys to their respective tables
SELECT name FROM student s , (SELECT studId FROM transcript  t, (SELECT crsCode FROM 
course WHERE deptId = 'deptId597183' )AS x WHERE t.crsCode = x.crsCode ) AS m WHERE 
s.id = m.studId;
