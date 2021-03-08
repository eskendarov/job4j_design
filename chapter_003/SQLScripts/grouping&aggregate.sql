create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people (
    id   serial primary key,
    name varchar(255)
);

create table devices_people (
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price)
values ('Smartphone', 55.6),
       ('Home PC', 123.5),
       ('Camera', 65.33),
       ('Headphones', 33);

insert into people(name)
values ('Vanya'),
       ('Dasha'),
       ('David'),
       ('Sonya');

insert into devices_people(device_id, people_id)
values (1, 1),
       (3, 1),
       (4, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (1, 3),
       (2, 3),
       (4, 3),
       (4, 4),
       (2, 4),
       (3, 4);

select avg(price)
from devices;

select p.name, avg(d.price)
from people as p
         join devices_people as dp
              on dp.people_id = p.id
         join devices as d
              on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p
         join devices_people as dp
              on dp.people_id = p.id
         join devices as d
              on dp.device_id = d.id
group by p.name
having avg(d.price) > 50;
