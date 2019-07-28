CREATE TABLE working_times
(
    working_time_id SERIAL PRIMARY KEY,
    start_hours     TIME NOT NULL,
    end_hours       TIME NOT NULL,
    mon             BOOLEAN DEFAULT false,
    tue             BOOLEAN DEFAULT false,
    wen             BOOLEAN DEFAULT false,
    thu             BOOLEAN DEFAULT false,
    fri             BOOLEAN DEFAULT false,
    sat             BOOLEAN DEFAULT false,
    sun             BOOLEAN DEFAULT false
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(50)
);

CREATE TABLE employees
(
    employee_id  SERIAL PRIMARY KEY,
    name         VARCHAR(128) NOT NULL,
    position     VARCHAR(30)  NOT NULL,
    login        VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(30)  NOT NULL,
    phone        VARCHAR(15)  NOT NULL UNIQUE,
    email        VARCHAR(50)  NOT NULL,
    working_time INTEGER      NOT NULL REFERENCES working_times (working_time_id),
    enabled      BOOLEAN DEFAULT TRUE,
    role         INTEGER      NOT NULL REFERENCES roles (role_id)
);

CREATE TABLE patients
(
    patient_id SERIAL PRIMARY KEY,
    name       VARCHAR(128)   NOT NULL,
    insurance  INTEGER UNIQUE NOT NULL
);

CREATE TABLE patient_cards
(
    patient_card_id  SERIAL PRIMARY KEY,
    patient          INTEGER                       NOT NULL REFERENCES patients (patient_id),
    status           VARCHAR(10) DEFAULT 'Treated' NOT NULL CHECK (status = 'Treated' OR status = 'Discharged'),
    attending_doctor INTEGER                       NOT NULL REFERENCES employees (employee_id),
    building         VARCHAR(2)                    NOT NULL CHECK ( building = 'A' OR building = 'B' OR building = 'C'),
    ward             INTEGER                       NOT NULL CHECK ( ward > 0 AND ward < 10 )
);

CREATE TABLE diagnoses
(
    diagnosis_id SERIAL PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    patient_card INTEGER     NOT NULL REFERENCES patient_cards (patient_card_id),
    start_date   TIMESTAMP DEFAULT now(),
    end_date     TIMESTAMP,
    comment      VARCHAR(50)
);

CREATE TABLE proceds_and_medics
(
    type_id SERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    kind    VARCHAR(12) CHECK ( kind = 'Medicament' OR kind = 'Procedure' )
);

CREATE TABLE prescriptions
(
    prescription_id    SERIAL PRIMARY KEY,
    patient            INTEGER   NOT NULL REFERENCES patients (patient_id),
    type               INTEGER REFERENCES proceds_and_medics (type_id),
    start_date         TIMESTAMP NOT NULL,
    end_date           TIMESTAMP NOT NULL,
    dose               VARCHAR(50),
    version            INTEGER DEFAULT 1,
    responsible_doctor INTEGER   NOT NULL REFERENCES employees (employee_id)
);

CREATE TABLE prescription_times
(
    prescription_time_id SERIAL PRIMARY KEY,
    prescription         INTEGER NOT NULL REFERENCES prescriptions (prescription_id),
    time                 TIME    NOT NULL
);

CREATE TABLE events
(
    event_id SERIAL PRIMARY KEY,
    patient  INTEGER REFERENCES patients (patient_id),
    date     TIMESTAMP   NOT NULL,
    status   VARCHAR(12) NOT NULL DEFAULT 'Planned' CHECK (status = 'Planned' OR status = 'In progress' OR status = 'Rejected'),
    type     INTEGER     NOT NULL REFERENCES proceds_and_medics (type_id),
    nurse    INTEGER REFERENCES employees (employee_id),
    comment  VARCHAR(50)
);

-- CREATE TABLE employees_roles
-- (
--     authority_id SERIAL PRIMARY KEY,
--     employee     INTEGER REFERENCES employees (employee_id),
--     role         INTEGER REFERENCES roles (role_id)
-- );

-- CREATE UNIQUE INDEX ix_empls_roles ON employees_roles (employee, role);
