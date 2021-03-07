CREATE TABLE passport (
    id     SERIAL PRIMARY KEY,
    serial INT,
    number INT
);
-- Если нам нужно получать паспорт по человеку:
CREATE TABLE people (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    passport_id INT REFERENCES passport (id) UNIQUE
);
-- Если нам нужно получать данные человека по паспорту и паспорт по человеку,
-- то мы можем сделать так:
CREATE TABLE passport (
    id     SERIAL PRIMARY KEY,
    serial INT,
    number INT
);
CREATE TABLE people (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE passport_people (
    id          SERIAL PRIMARY KEY,
    passport_id INT REFERENCES passport (id) UNIQUE,
    people_id   INT REFERENCES people (id) UNIQUE
);
