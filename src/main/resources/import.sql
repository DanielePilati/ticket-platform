INSERT INTO users (username, password, email) VALUES ('admin','{noop}admin', 'admin@boolean.com'),('user','{noop}user', 'user@boolean.com');
INSERT INTO roles (name) VALUES ('ADMIN'),('USER');
INSERT INTO users_roles (role_id, user_id) VALUES (1, 1),(2, 2);
