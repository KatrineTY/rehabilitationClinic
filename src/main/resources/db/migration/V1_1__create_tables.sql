CREATE TABLE working_times
(
    working_time_id SERIAL PRIMARY KEY,
    start_hours     TIME                  NOT NULL,
    end_hours       TIME                  NOT NULL,
    mon             BOOLEAN DEFAULT false NOT NULL,
    tue             BOOLEAN DEFAULT false NOT NULL,
    wen             BOOLEAN DEFAULT false NOT NULL,
    thu             BOOLEAN DEFAULT false NOT NULL,
    fri             BOOLEAN DEFAULT false NOT NULL,
    sat             BOOLEAN DEFAULT false NOT NULL,
    sun             BOOLEAN DEFAULT false NOT NULL
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(50)
);

CREATE TABLE employees
(
    employee_id  SERIAL PRIMARY KEY,
    name         VARCHAR(128)         NOT NULL,
    position     VARCHAR(30)          NOT NULL,
    login        VARCHAR(50)          NOT NULL UNIQUE,
    password     VARCHAR(60)          NOT NULL,
    phone        VARCHAR(15)          NOT NULL UNIQUE,
    email        VARCHAR(50)          NOT NULL,
    working_time INTEGER              NOT NULL REFERENCES working_times (working_time_id),
    enabled      BOOLEAN DEFAULT TRUE NOT NULL,
    role         INTEGER              NOT NULL REFERENCES roles (role_id)
);

CREATE TABLE patients
(
    patient_id SERIAL PRIMARY KEY,
    name       VARCHAR(128)       NOT NULL,
    insurance  VARCHAR(20) UNIQUE NOT NULL
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
    name         VARCHAR(50)             NOT NULL,
    patient_card INTEGER                 NOT NULL REFERENCES patient_cards (patient_card_id),
    start_date   TIMESTAMP DEFAULT now() NOT NULL,
    end_date     TIMESTAMP,
    comment      VARCHAR(50)
);

CREATE TABLE proceds_and_medics
(
    type_id SERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    kind    VARCHAR(12) CHECK ( kind = 'Medicament' OR kind = 'Procedure' ),
    UNIQUE (name, kind)
);

CREATE TABLE prescriptions
(
    prescription_id    SERIAL PRIMARY KEY,
    patient            INTEGER NOT NULL REFERENCES patients (patient_id),
    type               INTEGER REFERENCES proceds_and_medics (type_id),
    start_date         date    NOT NULL,
    end_date           date    NOT NULL,
    dose               VARCHAR(50),
    responsible_doctor INTEGER NOT NULL REFERENCES employees (employee_id)
);

CREATE TABLE prescription_times
(
    prescription_time_id SERIAL PRIMARY KEY,
    prescription         INTEGER NOT NULL REFERENCES prescriptions (prescription_id),
    time                 TIME    NOT NULL
);

CREATE TABLE events
(
    event_id   SERIAL PRIMARY KEY,
    patient    INTEGER REFERENCES patients (patient_id),
    building   VARCHAR(2)                    NOT NULL CHECK ( building = 'A' OR building = 'B' OR building = 'C'),
    ward       INTEGER                       NOT NULL CHECK ( ward > 0 AND ward < 10 ),
    date       TIMESTAMP                     NOT NULL,
    status     VARCHAR(12) DEFAULT 'Planned' NOT NULL CHECK (status = 'Planned' OR status = 'In progress' OR
                                                             status = 'Rejected' OR status = 'Finished'),
    type       INTEGER                       NOT NULL REFERENCES proceds_and_medics (type_id),
    dose       VARCHAR(50),
    nurse      INTEGER REFERENCES employees (employee_id),
    comment    VARCHAR(50),
    start_time TIME,
    end_time   TIME
);

-- CREATE TABLE employees_roles
-- (
--     authority_id SERIAL PRIMARY KEY,
--     employee     INTEGER REFERENCES employees (employee_id),
--     role         INTEGER REFERENCES roles (role_id)
-- );

-- CREATE UNIQUE INDEX ix_empls_roles ON employees_roles (employee, role);
