drop table customer if exists;

create table customer (
  id serial primary key,
  first_name varchar(255),
  last_name varchar(255)
);

drop table person if exists;

create table person (
  id varchar(255) primary key,
  name varchar(255),
  age int
);