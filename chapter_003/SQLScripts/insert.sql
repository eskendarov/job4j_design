INSERT INTO role(name)
VALUES ('admin'),
       ('owner'),
       ('user');
INSERT INTO users(name, role_id)
VALUES ('Alex', '1'),
       ('Maria', '2'),
       ('Adam', '3');
INSERT INTO rules(name)
VALUES ('free'),
       ('limited');
INSERT INTO role_rules(role_id, rules_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);
INSERT INTO category(name)
VALUES ('important'),
       ('urgent');
INSERT INTO state(name)
VALUES ('expected'),
       ('completed');
INSERT INTO item(message, users_id, category_id, state_id)
VALUES ('update', 1, 1, 1),
       ('check', 3, 2, 2);
INSERT INTO comments(description, item_id)
VALUES ('connection', 1),
       ('code style', 2);
INSERT INTO attaches(file, item_id)
VALUES ('log.txt', 1),
       ('rules.pdf', 2);
