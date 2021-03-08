create database inner_join_db;

CREATE TABLE author (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE book (
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255),
    pages     INT,
    author_id INT REFERENCES author (id)
);

INSERT INTO author(name)
VALUES ('Shildt'),
       ('Katy'),
       ('Popov'),
       ('Adithia'),
       ('Ekkel');

INSERT INTO book(name, pages, author_id)
VALUES ('Java', 343, 1),
       ('Head First', 245, 2),
       ('Algorithms', 222, 4),
       ('Java. Philosophy', 678, 5);

SELECT *
FROM author AS a
         INNER JOIN book AS b
                    ON a.id = b.author_id;

-- В PostgreSQL необязательно писать inner. Можно просто join.
SELECT *
FROM author AS a
         JOIN book AS b
              ON a.id = b.author_id
                  AND b.name LIKE 'J%';

SELECT author.name AS Автор,
       book.name   AS Книга,
       book.pages  AS Страниц
FROM author
         JOIN book
              ON author.id = book.author_id
                  AND pages > 230;
