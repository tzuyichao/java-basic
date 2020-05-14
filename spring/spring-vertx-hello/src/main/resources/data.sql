DROP TABLE IF EXISTS author;

CREATE TABLE author (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(200) NOT NULL,
  last_name VARCHAR(200) NOT NULL
);

INSERT INTO author (first_name, last_name) VALUES
  ('Enrich', 'Gamma'),
  ('Terence', 'Parr'),
  ('Matthias', 'Broecheler');


