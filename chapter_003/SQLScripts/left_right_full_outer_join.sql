-- 1. Создать таблицы и заполнить их начальными данными
create table departments (
    id   serial primary key,
    name varchar(255)
);

create table employees (
    id            serial primary key,
    name          varchar(255),
    department_id int references departments (id)
);

insert into departments(name)
values ('Health'),
       ('ITech'),
       ('Security'),
       ('Cleaning'),
       ('Library');

insert into employees(name, department_id)
values ('Denis', 1),
       ('Vera', 1),
       ('Nikolay', 3),
       ('Matvey', null),
       ('Sasha', 2),
       ('Emma', null),
       ('Angel', 5),
       ('Alex', 3),
       ('Ivan', 2);

-- 2. Выполнить запросы с left, right, full, cross соединениями
select d.name department,
       e.name employee
from departments d
         left join employees e
                   on d.id = e.department_id;

select d.name department,
       e.name employee
from departments d
         right join employees e
                    on d.id = e.department_id;

select d.name department,
       e.name employee
from departments d
         full join employees e
                   on d.id = e.department_id;

select d.name department,
       e.name employee
from departments d
         cross join employees e;

-- 3. Используя left join найти департаменты, у которых нет работников
select d.name department,
       e.name employee
from departments d
         left join employees e
                   on d.id = e.department_id
where e.department_id is null;

-- 4. Используя left и right join написать запросы,
--  которые давали бы одинаковый результат
select d.name department,
       e.name employee
from departments d
         left join employees e
                   on d.id = e.department_id;

select d.name department,
       e.name employee
from employees e
         right join departments d
                    on e.department_id = d.id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее
create table teens (
    id     serial primary key,
    name   varchar,
    gender char(1)
);

insert into teens(name, gender)
values ('Anton', 'M'),
       ('Danila', 'M'),
       ('Andrey', 'M'),
       ('Viktoria', 'F'),
       ('Maria', 'F'),
       ('Alexandra', 'F'),
       ('Tatyana', 'F'),
       ('Elena', 'F');

-- 6. Используя cross join составить все возможные разнополые пары
select boy.name  boys,
       girl.name girls
from teens boy
         cross join teens girl
where boy.gender like 'M'
  and girl.gender like 'F';
