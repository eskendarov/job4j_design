CREATE TABLE position (
    id   SERIAL PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE employees (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    position_id INT REFERENCES position (id)
);
INSERT INTO position(name)
VALUES ('programmer');
INSERT INTO employees(name, position_id)
VALUES ('Ivan', 1);
SELECT *
FROM employees;
SELECT *
FROM position
WHERE id IN (SELECT id FROM employees);