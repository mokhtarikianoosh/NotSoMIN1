--SQL queries after tuning and after adding primary and foreign keys
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
--Takes 0.00 sec to execute and this was from just adding id as the primary key which 
--creates an index for id for Student
SELECT name FROM Student WHERE id = 998983;

--2.List the names of students with id in the range of v2 (id) to v3 (inclusive).
--Takes 0.00 to 0.01 sec to execute and this was from just adding id as the primary 
--key which creates an index for id for Student
SELECT name FROM Student WHERE id >= 736096 AND id <= 998442;

--3.List the names of students who have taken course v4 (crsCode).
--Takes 0.00 sec to execute and this was just from adding id as the primary key which
--creates and index for id for Student
SELECT Student.name FROM Student, Transcript  WHERE Student.id = 
Transcript.studId AND Transcript.crsCode = 'crsCode579449';

--4.List the names of students who have taken a course taught by professor v5 (name).
--Takes 0.01 to 0.02 sec to execute from adding the primary key id for Student,
--primary key id for Professor
SELECT Student.name, Transcript.crsCode FROM Student,  Transcript, Teaching, Professor 
WHERE Student.id = Transcript.studId AND  Professor.id = Teaching.profId AND 
Professor.name = 'name260723';

--5.List the names of students who have taken a course from department v6 (deptId), 
--but not v7.
--Takes 0.00 to execute just from adding primary and foreign keys for the respective
--tables
SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  Transcript 
WHERE Student.id = Transcript.studId AND Course.crsCode = Transcript.crsCode AND 
Course.deptId = "deptId50411" AND Course.deptId <> "deptId563888";

--6.List the names of students who have taken all courses offered by department v8 
--(deptId).
--Takes 0.01 to 0.02 sec to execute just from adding primary keys and foreign keys 
--to their respective tables
 SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  
 Transcript WHERE Student.id = Transcript.studId AND Course.deptId = "deptId597183";
