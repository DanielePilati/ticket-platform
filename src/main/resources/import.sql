INSERT INTO users (username,password) VALUES ('admin','{noop}admin'),('user','{noop}user');
INSERT INTO roles (name) VALUES ('ADMIN'),('USER');
INSERT INTO users_roles (roles_id, user_id) VALUES (1, 1),(2, 2);