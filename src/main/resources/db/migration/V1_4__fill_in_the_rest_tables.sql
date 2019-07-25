INSERT INTO proceds_and_medics (name, kind)
VALUES ('Physiotherapy', 'Procedure');
INSERT INTO proceds_and_medics (name, kind)
VALUES ('Adrenalin', 'Medicament');
-------------------------------
INSERT INTO prescriptions (patient, type, start_date, end_date, responsible_doctor)
VALUES (1, 1, '2012-10-11', '2013-10-11', 1);
INSERT INTO prescriptions (patient, type, start_date, end_date, responsible_doctor)
VALUES (1, 2, '2012-10-11', '2013-10-01', 1);
-------------------------------
INSERT INTO prescription_times (prescription, time)
VALUES (1, '2012-10-11');
INSERT INTO prescription_times (prescription, time)
VALUES (2, '2012-10-12');
INSERT INTO prescription_times (prescription, time)
VALUES (1, '2012-10-13');
INSERT INTO prescription_times (prescription, time)
VALUES (1, '2012-10-14');
-------------------------------
INSERT INTO events (patient, date, status, type, nurse)
VALUES (1, '2012-10-11', 'Planned', 1, 1);
INSERT INTO events (patient, date, status, type, nurse)
VALUES (1, '2012-10-12', 'In progress', 1, 1);
INSERT INTO events (patient, date, status, type, nurse, comment)
VALUES (1, '2012-10-13', 'Rejected', 2, 1, 'Some comment');
INSERT INTO events (patient, date, status, type, nurse)
VALUES (1, '2012-10-14', 'Planned', 1, 1);
