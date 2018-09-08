DROP DATABASE IF EXISTS test;
CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8 COLLATE utf8_general_ci;

USE test;
drop table if exists part;
create table part (
id bigint not null auto_increment,
name varchar(255),
need bit not null,
quantity_in_stock varchar(255),
primary key (id)
);

insert into part (id,name, need, quantity_in_stock) values (1, 'HDD', true, 6);
insert into part (id,name, need, quantity_in_stock) values (2, 'SSD', false , 2);
insert into part (id,name, need, quantity_in_stock) values (3, 'Материнская плата', true, 3);
insert into part (id,name, need, quantity_in_stock) values (4, 'процессор', true, 4);
insert into part (id,name, need, quantity_in_stock) values (5, 'подсветка корпуса', false , 5);
insert into part (id,name, need, quantity_in_stock) values (6, 'звуковая карта', true , 6);
insert into part (id,name, need, quantity_in_stock) values (7, 'корпус', true, 7);
insert into part (id,name, need, quantity_in_stock) values (8, 'видеокарта', true , 8);
insert into part (id,name, need, quantity_in_stock) values (9, 'DDR3 память', true, 9);
insert into part (id,name, need, quantity_in_stock) values (10, 'наушники', false , 10);
insert into part (id,name, need, quantity_in_stock) values (11, 'монитор', false, 11);
insert into part (id,name, need, quantity_in_stock) values (12, 'клавиатура', false, 12);
insert into part (id,name, need, quantity_in_stock) values (13, 'мышка', false, 13);
insert into part (id,name, need, quantity_in_stock) values (14, 'HDMI', false , 14);
insert into part (id,name, need, quantity_in_stock) values (15, 'блок питания', true , 15);
insert into part (id,name, need, quantity_in_stock) values (16, 'антивирус', false , 100);
insert into part (id,name, need, quantity_in_stock) values (17, 'офисный пакет', false , 15);
insert into part (id,name, need, quantity_in_stock) values (18, 'сетевая карта', true, 5);
insert into part (id,name, need, quantity_in_stock) values (19, 'кард ридер', false , 15);
insert into part (id,name, need, quantity_in_stock) values (20, 'система охлаждения процессора', true, 15);
insert into part (id,name, need, quantity_in_stock) values (21, 'оптический привод', false, 15);
insert into part (id,name, need, quantity_in_stock) values (22, 'WI-FI', false , 3);

