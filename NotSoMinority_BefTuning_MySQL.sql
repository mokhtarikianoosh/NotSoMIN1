--SQL queries before tuning and before adding any primary or foreign keys
--studName in Student is used to refer to the student's name
USE Project3;
--1.List the name of the student with id equal to v1 (id).
--Takes 0.00 to 0.01 sec to execute
SELECT name FROM Student WHERE id = 998983;

--2.List the names of students with id in the range of v2 (id) to v3 (inclusive).
--Takes 0.00 to 0.01 sec to execute
SELECT name FROM Student WHERE id >= 927358 AND id <= 998442;

--Finds all the students whose ids match from Transcript and have taken courses
--Takes 1.95 to 2.49 sec to execute
SELECT Student.name, Student.id, Transcript.crsCode FROM Student, Transcript 
WHERE Student.id = Transcript.studId;
--3.List the names of students who have taken course v4 (crsCode).
--Takes 0.01 to 0.02 sec to execute
SELECT Student.name FROM Student, Transcript  WHERE Student.id = 
Transcript.studId AND Transcript.crsCode = 'crsCode579449';

--Returns all professors teaching a course
--Takes 2.67 to 2.81 sec to execute
SELECT Professor.name, Teaching.crsCode FROM Professor, Teaching WHERE 
Professor.id = Teaching.profId;
--4.List the names of students who have taken a course taught by professor v5 (name).
--Takes 17.78 to 18.70 sec to execute
SELECT Student.name, Transcript.crsCode FROM Student,  Transcript, Teaching, Professor 
HERE Student.id = Transcript.studId AND  Professor.id = Teaching.profId AND 
Professor.name = 'name260723';

--Finds all the students who take courses from any department
--Takes 4.38 to 4.43 sec to execute
SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course, 
Transcript WHERE Student.id = Transcript.studId AND Course.crsCode = Transcript.crsCode;
--5.List the names of students who have taken a course from department v6 (deptId), 
--but not v7.
--Takes 0.01 to 0.02 sec to execute
SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  Transcript 
WHERE Student.id = Transcript.studId AND Course.crsCode = Transcript.crsCode AND 
Course.deptId = "deptId50411" AND Course.deptId <> "deptId563888";

--6.List the names of students who have taken all courses offered by department v8 
--(deptId).
--Takes 3.10 to 3.26 to execute
 SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  
 Transcript WHERE Student.id = Transcript.studId AND Course.deptId = "deptId597183";





