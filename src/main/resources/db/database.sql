drop table if exists part
create table part (
id bigint not null auto_increment,
name varchar(255),
need bit not null,
quantity_in_stock varchar(255),
primary key (id);
);
