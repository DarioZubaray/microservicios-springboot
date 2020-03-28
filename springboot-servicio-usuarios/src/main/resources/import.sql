INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$.54WeDAAZmi/oyu1RBeH2eXRaaKcXN1hufY04B.6HgXrb7O48k7t2', 1, 'DJ', 'Zubaray', 'djzubaray@gmail.com')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('dario', '$2a$10$MDMyDuoy9zIWOakRlfPmiuB8B7OJe7g2e0tmxu5tZn.PCAR.M2Aou', 1, 'Dario', 'Zubaray', 'dzubaray@gmail.com')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('julieta', '$2a$10$VmKn.LUCZ2FHC29n2dhe8uXIzh3VJa.8EfuuSVvUOPoQ8o05N6Yge', 1, 'Julieta', 'Zubaray', 'jzubaray@gmail.com')
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('valentina', '$2a$10$5d5Q9t7rzWbrpktgLJHjTeAN.mTrbVD54S5l/.umLaqW0P4jGtBKm', 1, 'JV', 'Zubaray', 'jvzubaray@gmail.com')

INSERT INTO roles (nombre) VALUES ('ROLE_USER')
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN')

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1)
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2)
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1)
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1)
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4, 1)
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4, 2)