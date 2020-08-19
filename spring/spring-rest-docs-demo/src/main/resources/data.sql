DROP TABLE IF EXISTS system_user;

CREATE TABLE system_user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  UNIQUE user_uk_1 (email)
);
