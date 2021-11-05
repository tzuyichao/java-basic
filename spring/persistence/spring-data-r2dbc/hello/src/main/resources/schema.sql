drop table customer if exists;

create table customer (
  id serial primary key,
  first_name varchar(255),
  last_name varchar(255)
);