INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('dario', '12345', 1, 'Dario', 'Zubaray', 'dzubaray@gmail.com')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('julieta', '12345', 1, 'Julieta', 'Zubaray', 'jzubaray@gmail.com')

INSERT INTO roles (nombre) VALUES ('ROLE_USER')
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN')

INSERT INTO usuarios_roles (usuarios_id, role_id) VALUES (1, 1)
INSERT INTO usuarios_roles (usuarios_id, role_id) VALUES (1, 2)
INSERT INTO usuarios_roles (usuarios_id, role_id) VALUES (2, 1)