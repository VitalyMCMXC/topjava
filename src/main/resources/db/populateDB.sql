DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (userId, datetime, description, calories ) VALUES
  (100000, localtimestamp, 'Завтрак', 500),
  (100000, localtimestamp, 'Обед', 1000),
  (100000, localtimestamp, 'Ужин', 500),
  (100000, make_timestamp(2015, 12, 31, 09, 00, 00), 'Завтрак', 500),
  (100000, make_timestamp(2015, 12, 31, 14, 00, 00), 'Обед', 1000),
  (100000, make_timestamp(2015, 12, 31, 23, 00, 00), 'Ужин', 900),
  (100001, make_timestamp(2015, 12, 31, 15, 00, 00), 'ЕДА', 2011);
