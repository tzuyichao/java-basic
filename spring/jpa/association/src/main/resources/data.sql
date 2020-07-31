DROP TABLE IF EXISTS author;

DROP TABLE IF EXISTS book;

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