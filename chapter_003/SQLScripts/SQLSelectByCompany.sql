-- 1. Таблица company
CREATE TABLE company (
    id   INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
-- 2. Таблица person
CREATE TABLE person (
    id         INTEGER NOT NULL,
    name       CHARACTER VARYING,
    company_id INTEGER,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name)
VALUES (1, 'JetBrains');
INSERT INTO company(id, name)
VALUES (5, 'GeekBrains');
INSERT INTO company(id, name)
VALUES (3, 'GazProm');

INSERT INTO person(id, name, company_id)
VALUES (2, 'Ivan', 5);
INSERT INTO person(id, name, company_id)
VALUES (5, 'Irina', 5);
INSERT INTO person(id, name, company_id)
VALUES (6, 'Andrey', 5);
INSERT INTO person(id, name, company_id)
VALUES (3, 'Fedor', 1);
INSERT INTO person(id, name, company_id)
VALUES (4, 'Marina', 3);

-- Получить имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.
SELECT p.name person, c.name company
FROM person p
         join company c on p.company_id = c.id
WHERE c.id != 5;


-- Необходимо выбрать название компании с максимальным
-- количеством человек + количество человек в этой компании.
SELECT c.name company, count(p.name) person_count
FROM company c
         join person p on c.id = p.company_id
GROUP BY c.name
HAVING count(p.name) = (SELECT count(company_id) cp
                        FROM person
                        GROUP BY company_id
                        ORDER BY cp DESC
                        LIMIT 1
);
