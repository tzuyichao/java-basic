DROP TABLE IF EXISTS book;

DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id INT AUTO_INCREMENT PRIMARY KEY,
  age INT,
  genre VARCHAR(255),
  name VARCHAR(255),
  created TIMESTAMP,
  modified TIMESTAMP
);

CREATE TABLE book (
  id INT AUTO_INCREMENT PRIMARY KEY,
  author_id INT,
  isbn VARCHAR(255),
  title VARCHAR(255),
  created TIMESTAMP,
  modified TIMESTAMP,
  FOREIGN KEY (author_id) REFERENCES author(id)
);

DROP TABLE IF EXISTS singer_instrument;
DROP TABLE IF EXISTS instrument;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS singer;

CREATE TABLE singer (
  id int auto_increment primary key,
  first_name varchar(60) not null,
  last_name varchar(40) not null,
  birth_date date,
  version int not null default 0,
  unique uq_singer_1 (first_name, last_name)
);

CREATE TABLE album (
  id int auto_increment primary key,
  singer_id int not null,
  title varchar(100) not null,
  release_date date,
  version int not null default 0,
  unique uq_singer_album_1 (singer_id, title),
  FOREIGN KEY (singer_id) REFERENCES singer(id)
);

CREATE TABLE instrument (
  instrument_id varchar(20) primary key
);

CREATE TABLE singer_instrument (
  singer_id int not null,
  instrument_id varchar(20) not null,
  primary key (singer_id, instrument_id),
  FOREIGN KEY (singer_id) REFERENCES singer(id),
  FOREIGN KEY (instrument_id) REFERENCES instrument(instrument_id)
);