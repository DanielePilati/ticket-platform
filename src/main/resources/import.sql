INSERT INTO users (username, password, email, not_available) VALUES ('admin','{noop}admin', 'admin@boolean.com', true),('user','{noop}user', 'user@boolean.com', false),('Operators Not Available','{noop}admin', 'assistance@boolean.com', false);
INSERT INTO roles (name) VALUES ('ADMIN'),('USER');
INSERT INTO users_roles (roles_id, user_id) VALUES (1, 1),(2, 2);
