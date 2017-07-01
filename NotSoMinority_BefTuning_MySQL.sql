--SQL queries before tuning and before adding any primary or foreign keys
--studName in Student is used to refer to the student's name
USE Project3;
--1.List the name of the student with id equal to v1 (id).
--Takes 0.00 to 0.01 sec to execute
SELECT studName FROM Student WHERE id = 998983;

--2.List the names of students with id in the range of v2 (id) to v3 (inclusive).
--Takes 0.00 to 0.01 sec to execute
SELECT studName FROM Student WHERE id >= 927358 AND id <= 998442;

--Finds all the students whose ids match from Transcript and have taken courses
--Takes 2.04 to 2.18 sec to execute
SELECT Student.studName, Student.id, Transcript.crsCode FROM Student, Transcript 
WHERE Student.id = Transcript.studId;
--3.List the names of students who have taken course v4 (crsCode).


--4.List the names of students who have taken a course taught by professor v5 (name).

--5.List the names of students who have taken a course from department v6 (deptId), 
--but not v7.

--6.List the names of students who have taken all courses offered by department v8 (deptId).





