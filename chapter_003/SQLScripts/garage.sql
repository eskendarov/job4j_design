create table car (
    id   serial primary key,
    name varchar
);

create table body (
    id     serial primary key,
    name   varchar,
    car_id int references car (id)
);

create table engine (
    id     serial primary key,
    name   varchar,
    car_id int references car (id)
);

create table gearbox (
    id     serial primary key,
    name   varchar,
    car_id int references car (id)
);

insert into car(name)
values ('Ferrari'),
       ('Mercedes'),
       ('BMW'),
       ('Dodge'),
       ('Rolls-Royce');

insert into body(name, car_id)
values ('Red', null),
       ('Black', 3),
       ('White', 1),
       ('Cyan', 2),
       ('Grey', 5),
       ('Green', 4);

insert into engine(name, car_id)
values ('Engine V6.1', 2),
       ('Engine V4.2', 5),
       ('Engine V5.4', 4),
       ('Engine V3.3', 3),
       ('Engine V3.5', null),
       ('Engine V5.6', 1);

insert into gearbox(name, car_id)
values ('Robot G2.2', 2),
       ('Automatic', 5),
       ('Manual', 3),
       ('Robot G2.2', 4),
       ('Automatic', 1),
       ('Automatic One', null);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name car, b.name body, e.name engine, g.name gearbox
from car c
         left join body b on c.id = b.car_id
         left join engine e on c.id = e.car_id
         left join gearbox g on c.id = g.car_id;

-- 2. Вывести отдельно детали (1 деталь - 1 запрос),
--  которые не используются в машине: кузова, двигатели, коробки передач.
select b.name body
from body b
         left join car c1 on b.car_id = c1.id
where b.car_id is null;

select e.name engine
from engine e
         left join car c2 on e.car_id = c2.id
where e.car_id is null;

select g.name gearbox
from gearbox g
         left join car c3 on g.car_id = c3.id
where g.car_id is null;

