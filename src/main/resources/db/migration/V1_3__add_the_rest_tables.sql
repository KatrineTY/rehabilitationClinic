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
    version            INTEGER   NOT NULL DEFAULT 1,
    responsible_doctor INTEGER   NOT NULL REFERENCES employees (employee_id)
);

CREATE TABLE prescription_times
(
    prescription_time_id SERIAL PRIMARY KEY,
    prescription         INTEGER   NOT NULL REFERENCES prescriptions (prescription_id),
    time                 TIMESTAMP NOT NULL
);

CREATE TABLE events
(
    event_id SERIAL PRIMARY KEY,
    patient  INTEGER REFERENCES patients (patient_id),
    date     TIMESTAMP   NOT NULL,
    status   VARCHAR(12) NOT NULL CHECK (status = 'Planned' OR status = 'In progress' OR status = 'Rejected'),
    type     INTEGER REFERENCES proceds_and_medics (type_id),
    nurse    INTEGER REFERENCES employees (employee_id),
    comment  VARCHAR(50)
)