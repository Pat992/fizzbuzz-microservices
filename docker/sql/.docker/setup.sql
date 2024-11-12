CREATE DATABASE IF NOT EXISTS fizzbuzz_gateway;
CREATE DATABASE IF NOT EXISTS fizzbuzz_database;
CREATE DATABASE IF NOT EXISTS logging_database;

GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `fizzbuzz_gateway`.* TO 'fizz'@'%';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `fizzbuzz_database`.* TO 'fizz'@'%';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `logging_database`.* TO 'fizz'@'%';

FLUSH PRIVILEGES;

USE fizzbuzz_gateway;

CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);

INSERT INTO users (username, password, enabled)
  values
  ('fizz', '$2a$12$PWyKHoaVliErZY3CHrOOO.AjsmSAyYquRSrYIMyqe18AX1cDieT1W', 1),
  ('buzz', '$2a$12$27xe8Dzs7ckhHS5Ktj8wDuJRiydKaHj7QUgD6BOcBt8KXvFYsDT8y', 1),
  ('admin', '$2a$12$gJ09OVF8A.NnJMOdnxTkAul4pSYb.DMZLigJny.3OqiBjtVMSebjC', 1);

INSERT INTO authorities (username, authority)
  values
  ('fizz', 'ROLE_EMPLOYEE'),
  ('buzz', 'ROLE_EMPLOYEE'),
  ('admin', 'ROLE_EMPLOYEE'),
  ('admin', 'ROLE_ADMIN');