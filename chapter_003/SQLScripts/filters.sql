create table typeid (
    id   serial primary key,
    name varchar(255)
);

create table product (
    id           serial primary key,
    name         varchar(255),
    type_id      int references typeid (id),
    expired_date date,
    price        float
);

insert into typeid(name)
values ('Сыр'),
       ('Мясо'),
       ('Молочка'),
       ('Бакалея'),
       ('Заморозка');

insert into product(name, type_id, expired_date, price)
values ('Российский', 1, '2021-03-25', 450),
       ('Голландский', 1, '2021-03-18', 560),
       ('Гауда', 1, '2021-03-28', 480),
       ('Говядина', 2, '2021-03-15', 430),
       ('Баранина', 2, '2021-03-16', 520),
       ('Кефир', 3, '2021-03-11', 45),
       ('Молоко', 3, '2021-03-12', 51),
       ('Молоко', 3, '2021-03-12', 51),
       ('Ряженка', 3, '2021-03-25', 56),
       ('Рис', 4, '2021-09-24', 86),
       ('Гречка', 4, '2022-01-18', 74),
       ('Мороженное Пломбир', 5, '2021-09-10', 68),
       ('Мороженное Фисташковое', 5, '2021-09-10', 88),
       ('Рыба_1', 5, '2022-01-11', 245),
       ('Рыба_2', 5, '2022-02-12', 356),
       ('Рыба_3', 5, '2022-03-13', 267),
       ('Рыба_4', 5, '2022-04-14', 178),
       ('Рыба_5', 5, '2022-05-15', 189),
       ('Рыба_6', 5, '2022-06-16', 910),
       ('Рыба_7', 5, '2022-07-17', 111),
       ('Рыба_8', 5, '2022-08-18', 125),
       ('Рыба_9', 5, '2022-09-19', 135);

-- выводит все продукты с типом "СЫР"
select p.name, p.expired_date, p.price
from product as p
         join typeid as t
              on t.id = p.type_id
                  and t.name like 'Сыр';

-- выводит все продукты, у которых в имени есть слово "мороженное"
select p.name, p.expired_date, p.price
from product as p
where name like '%Мороженное%';

-- выводит продукты, срок годности которых заканчивается в следующем месяце
select p.name, p.expired_date, p.price
from product as p
where p.expired_date between current_date
          and current_date + interval '1month';

-- выводит самый дорогой продукт
select p.name, p.price
from product p
where price = (select max(price) from product);

-- выводит количество всех продуктов определенного типа
select count(p)
from product as p
         join typeid as t
              on t.id = p.type_id
                  and t.name like 'Мясо';

-- выводит все продукты с типом "СЫР" и "МОЛОКО"
select p.name, p.expired_date, p.price
from product as p
         join typeid as t
              on t.id = p.type_id
                  and (t.name like 'Сыр' or t.name like 'Молочка');

-- выводит тип продуктов, которых осталось меньше 10 штук
select t.name, count(p)
from product as p
         join typeid t on t.id = p.type_id
group by t.name
having count(p) < 10;

-- выводит все продукты и их тип
select t.name, p.name, p.expired_date, p.price
from product p
         join typeid t
              on p.type_id = t.id;
