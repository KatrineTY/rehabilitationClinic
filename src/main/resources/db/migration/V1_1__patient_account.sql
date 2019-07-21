CREATE TABLE employee
(
    employee_id  SERIAL PRIMARY KEY,
    name         VARCHAR(128) NOT NULL,
    position     VARCHAR(30)  NOT NULL,
    login        VARCHAR(30)  NOT NULL UNIQUE,
    password     VARCHAR(30)  NOT NULL,
    phone        VARCHAR(15)  NOT NULL UNIQUE,
    email        VARCHAR(30)  NOT NULL,
    working_time VARCHAR(20)  NOT NULL
);

CREATE TABLE patient
(
    patient_id       SERIAL PRIMARY KEY,
    name             VARCHAR(128)   NOT NULL,
    diagnosis        VARCHAR(30)    NOT NULL,
    insurance        INTEGER UNIQUE NOT NULL,
    status           VARCHAR(10)    NOT NULL CHECK (status = 'Treated' OR status = 'Discharged'),
    attending_doctor INTEGER        NOT NULL REFERENCES employee (employee_id),
    building         VARCHAR(2)     NOT NULL,
    ward             INTEGER        NOT NULL
);

INSERT INTO employee (name, position, login, password, phone, email, working_time)
VALUES ('doctor name', 'main doctor', 'login', 'password', 'some phone', 'some email', 'some time');

INSERT INTO patient (name, diagnosis, insurance, status, attending_doctor, building, ward)
VALUES ('patient name1', 'some diagnosis1', '12345678', 'Treated', 1, 'A', 1);

INSERT INTO patient (name, diagnosis, insurance, status, attending_doctor, building, ward)
VALUES ('patient name2', 'some diagnosis2', '123456789', 'Treated', 1, 'A', 2);

