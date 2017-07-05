--SQL queries before tuning and before adding any primary or foreign keys
CREATE DATABASE Project3_Before;
USE Project3_Before;
--1.List the name of the student with id equal to v1 (id).
--Takes 0.0034 to 0.0060 sec to execute
SELECT name FROM Student WHERE id = 995293;

--2.List the names of students with id in the range of v2 (id) to v3 (inclusive).
--Takes 0.0037 to 0.0039 sec to execute
SELECT name FROM Student WHERE id >= 131 AND id <= 5379;

--Finds all the students whose ids match from Transcript and have taken courses
--Takes 1.95 to 2.49 sec to execute
--SELECT Student.name, Student.id, Transcript.crsCode FROM Student, Transcript 
--WHERE Student.id = Transcript.studId;
--3.List the names of students who have taken course v4 (crsCode).
--Takes 0.0083 to 0.0091 sec to execute
SELECT Student.name FROM Student, Transcript  WHERE Student.id = 
Transcript.studId AND Transcript.crsCode = 'crsCode579449';

--Returns all professors teaching a course
--Takes 2.67 to 2.81 sec to execute
--SELECT Professor.name, Teaching.crsCode FROM Professor, Teaching WHERE 
--Professor.id = Teaching.profId;
--4.List the names of students who have taken a course taught by professor v5 (name).
--Takes 0.025 to 0.028 sec to execute
SELECT Student.name, Transcript.crsCode FROM Student,  Transcript, Teaching, Professor 
WHERE Student.id = Transcript.studId AND  Professor.id = Teaching.profId AND 
Transcript.crsCode = Teaching.crsCode AND Professor.name = 'name260723';

--Finds all the students who take courses from any department
--Takes 4.38 to 4.43 sec to execute
--SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course, 
--Transcript WHERE Student.id = Transcript.studId AND Course.crsCode = Transcript.crsCode;
--5.List the names of students who have taken a course from department v6 (deptId), 
--but not v7.
--Takes 0.016 to 0.020 sec to execute
SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  
Transcript WHERE Student.id = Transcript.studId AND Course.crsCode = Transcript.crsCode 
AND Course.deptId = "deptId50411" AND student.id not IN (SELECT studId FROM transcript 
WHERE crsCode  IN (SELECT crsCode FROM course WHERE deptId= "deptId563888"));

--6.List the names of students who have taken all courses offered by department v8 
--(deptId).
--Takes 0.0083 to 0.0085 to execute
SELECT Student.name, Transcript.crsCode, Course.deptId FROM Student, Course,  
Transcript WHERE Student.id = Transcript.studId AND Course.deptId = "deptId597183" 
AND course.crsCode = transcript.crsCode;





