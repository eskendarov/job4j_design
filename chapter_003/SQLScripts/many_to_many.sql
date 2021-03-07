CREATE TABLE students (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE courses (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE students_courses (
    id         SERIAL PRIMARY KEY,
    student_id INT REFERENCES students (id),
    course_id  INT REFERENCES courses (id)
);
INSERT INTO students(name)
VALUES ('Ivan');
INSERT INTO students(name)
VALUES ('Kirill');
INSERT INTO students(name)
VALUES ('Roman');
INSERT INTO courses(name)
VALUES ('Java SE');
INSERT INTO courses(name)
VALUES ('Spring');
INSERT INTO courses(name)
VALUES ('Hibernate');
INSERT INTO students_courses(student_id, course_id)
VALUES (1, 1);
INSERT INTO students_courses(student_id, course_id)
VALUES (1, 2);
INSERT INTO students_courses(student_id, course_id)
VALUES (1, 3);
INSERT INTO students_courses(student_id, course_id)
VALUES (2, 1);
INSERT INTO students_courses(student_id, course_id)
VALUES (2, 2);
INSERT INTO students_courses(student_id, course_id)
VALUES (1, 3);