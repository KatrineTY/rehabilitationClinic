CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(50)
);

CREATE TABLE employees_roles
(
    authority_id SERIAL PRIMARY KEY,
    employee     INTEGER REFERENCES employees (employee_id),
    role         INTEGER REFERENCES roles (role_id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER');


INSERT INTO employees_roles (employee, role)
VALUES (1, 1);