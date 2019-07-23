DROP TABLE patient;

CREATE TABLE patients
(
    patient_id SERIAL PRIMARY KEY,
    name       VARCHAR(128)   NOT NULL,
    insurance  INTEGER UNIQUE NOT NULL
);

CREATE TABLE patient_cards
(
    patient_card_id  SERIAL PRIMARY KEY,
    patient          INTEGER     NOT NULL REFERENCES patients (patient_id),
    status           VARCHAR(10) NOT NULL CHECK (status = 'Treated' OR status = 'Discharged'),
    attending_doctor INTEGER     NOT NULL REFERENCES employee (employee_id),
    building         VARCHAR(2)  NOT NULL,
    ward             INTEGER     NOT NULL
);

CREATE TABLE diagnoses
(
    diagnosis_id SERIAL PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    patient_card INTEGER     NOT NULL REFERENCES patient_cards (patient_card_id),
    start_date   TIMESTAMP   NOT NULL DEFAULT now(),
    end_date     TIMESTAMP,
    comment      VARCHAR(50)
);

ALTER TABLE employee
    RENAME TO employees;
---------------------------------------
INSERT INTO patients (name, insurance)
VALUES ('patient name1', '12345678');

INSERT INTO patient_cards (patient, status, attending_doctor, building, ward)
VALUES (1, 'Treated', 1, 'A', 1);

INSERT INTO diagnoses (name, patient_card)
VALUES ('some diagnosis1', 1);
---------------------------------------
INSERT INTO diagnoses (name, patient_card)
VALUES ('some diagnosis3', 1);
---------------------------------------
INSERT INTO patients (name, insurance)
VALUES ('patient name2', '123456789');

INSERT INTO patient_cards (patient, status, attending_doctor, building, ward)
VALUES (2, 'Treated', 1, 'A', 2);

INSERT INTO diagnoses (name, patient_card)
VALUES ('some diagnosis2', 2);