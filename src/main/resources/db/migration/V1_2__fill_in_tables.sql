-- -------------------------------
-- INSERT INTO prescription_times (prescription, time)
-- VALUES (1, '2012-10-11');
-- INSERT INTO prescription_times (prescription, time)
-- VALUES (2, '2012-10-12');
-- INSERT INTO prescription_times (prescription, time)
-- VALUES (1, '2012-10-13');
-- INSERT INTO prescription_times (prescription, time)
-- VALUES (1, '2012-10-14');
-- -------------------------------
-- INSERT INTO events (patient, date, status, type, nurse)
-- VALUES (1, '2012-10-11', 'Planned', 1, 1);
-- INSERT INTO events (patient, date, status, type, nurse)
-- VALUES (1, '2012-10-12', 'In progress', 1, 1);
-- INSERT INTO events (patient, date, status, type, nurse, comment)
-- VALUES (1, '2012-10-13', 'Rejected', 2, 1, 'Some comment');
-- INSERT INTO events (patient, date, status, type, nurse)
-- VALUES (1, '2012-10-14', 'Planned', 1, 1);
--

-- INSERT INTO employees_roles (employee, role)
-- VALUES (1, 1);

INSERT INTO "patients" (name, insurance)
VALUES ('Anne Beck', '99532443'),
       ('Unity Mccullough', '71449712'),
       ('Bernard Maldonado', '52535505'),
       ('Noble Sawyer', '54864812'),
       ('Deacon Fox', '83037978'),
       ('Chester Shields', '55096367'),
       ('Murphy Cooley', '19506853'),
       ('Jaquelyn Howard', '22730869'),
       ('Steven Pollard', '72023967'),
       ('Blossom Dominguez', '40184068');

INSERT INTO working_times (start_hours, end_hours, mon, tue, wen, thu, fri, sat, sun)
VALUES ('08:00:00', '16:00:00', true, true, true, true, true, false, false), -- doctors
       ('06:00:00', '14:00:00', true, true, true, true, true, false, false), -- nurses
       ('14:00:00', '22:00:00', true, true, true, true, true, false, false),
       ('22:00:00', '06:00:00', true, true, true, true, true, false, false),
       ('08:00:00', '20:00:00', false, false, false, false, false, true, true),
       ('20:00:00', '08:00:00', false, false, false, false, false, true, true);

--        ('08:00:00', '16:00:00', false, false, false, true, true, false, false), -- nurses, --nurse1
--        ('16:00:00', '00:00:00', true, false, false, false, false, false, false),
--        ('00:00:00', '08:00:00', false, true, true, false, false, false, false),
--
--        ('08:00:00', '16:00:00', false, false, false, false, true, true, false), -- nurse2
--        ('16:00:00', '00:00:00', false, true, false, false, false, false, false),
--        ('00:00:00', '08:00:00', false, false, true, true, false, false, false),
--
--        ('08:00:00', '16:00:00', false, false, false, false, false, true, true), --nurse3
--        ('16:00:00', '00:00:00', false, false, true, false, false, false, false),
--        ('00:00:00', '08:00:00', false, false, false, true, true, false, false),
--
--        ('08:00:00', '16:00:00', true, false, false, false, false, false, true), --nurse4
--        ('16:00:00', '00:00:00', false, false, false, true, false, false, false),
--        ('00:00:00', '08:00:00', false, false, false, false, true, true, false),
--
--        ('08:00:00', '16:00:00', true, true, false, false, false, false, false), --nurse5
--        ('16:00:00', '00:00:00', false, false, false, false, true, false, false),
--        ('00:00:00', '08:00:00', false, false, false, false, false, true, true),
--
--        ('08:00:00', '16:00:00', false, true, true, false, false, false, false), --nurse6
--        ('16:00:00', '00:00:00', false, false, false, false, false, true, false),
--        ('00:00:00', '08:00:00', true, false, false, false, false, false, true),
--
--        ('08:00:00', '16:00:00', false, false, true, true, false, false, false), --nurse7
--        ('16:00:00', '00:00:00', false, false, false, false, false, false, true),
--        ('00:00:00', '08:00:00', true, true, false, false, false, false, false);

INSERT INTO roles (name)
VALUES ('ROLE_MAIN_DOCTOR'),
       ('ROLE_NURSE'),
       ('ROLE_ADMINISTRATOR'),
       ('ROLE_DOCTOR');

INSERT INTO "employees" (name, position, login, password, phone, email, working_time, role)
VALUES ('Zahir Durham', 'attending doctor', 'zahir.durham',
        '$2a$11$58tVkEfWNoigOmFI/5CxPOMaj6UU4W17VhlxfcJ1AyHuQJWGV4tW2', '1-174-699-7045',
        'mauris@arcuNunc.edu', 1, 1),
       ('Berk Estrada', 'attending doctor', 'berk.estrada',
        '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym', '1-159-671-7913',
        'auctor.nunc.nulla@estvitae.edu', 1, 1),

       ('Kelsey Fulton', ' nurse', 'kelsey.fulton', '$2a$11$vua77rQXEvcKoIXa8Kmw0.C.YRxH.UKL6xK09jzrtY9ij/mOKl/qO',
        '1-460-421-3616',
        'nunc@eleifendCras.co.uk', 2, 2),
       ('Cathleen Bailey', 'nurse', 'cathleen.bailey', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-181-662-0346',
        'Integer@auguemalesuada.org', 2, 2),
       ('Trevor Holman', 'nurse', 'trevor.holman', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-793-933-1304',
        'nisi@vulputatenisi.co.uk', 3, 2),
       ('Russell Blackburn', ' nurse', 'russell.blackburn',
        '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym', '1-593-892-9513',
        'suscipit.est@faucibus.edu', 3, 2),
       ('Orli Hooper', 'nurse', 'orli.hooper', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-631-482-6233',
        'euismod.est@luctusfelispurus.org', 4, 2),
       ('Louis Holt', 'nurse', 'louis.holt', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-380-330-0080',
        'Maecenas.ornare.egestas@at.org', 5, 2),
       ('Chandler Hester', 'nurse', 'chandler.hester', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-384-153-2692',
        'laoreet.lectus@eterosProin.edu', 6, 2),

       ('Skyler Pope', ' doctor', 'skyler.rope', '$2a$11$mDJXVc3db46nnWeaVE.dVOpAQwE7lSnteuqniowtuW0VdY05ljdqO',
        '1-400-129-4351',
        'facilisis.non.bibendum@nislMaecenasmalesuada.org', 1, 4),
       ('Tana Hebert', ' doctor', 'iana.hobert', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-857-866-2914',
        'mattis.Cras.eget@elitafeugiat.edu', 1, 4),
       ('Troy Garcia', ' doctor', 'troy.garcia', '$2a$11$XuqE7HbYvTsfY2PayDXhN.0VXNzD9dbHkrdBozqdiYuMUCawU2Lym',
        '1-554-148-3166',
        'elit.elit.fermentum@inhendrerit.co.uk', 1, 4),

       ('Katrine F.', ' administrator', 'login',
        '$2a$10$ftSAE5IBQHG6P8c9HBnNPOZJX1EP/80/MtboQiB7sn7JoIh.bt4AK', '1-554-148-3966', 'katrine@gmail.com', 1, 3);

INSERT INTO "patient_cards" (patient, building, ward, attending_doctor)
VALUES (1, 'A', 5, 2),
       (2, 'B', 3, 1),
       (3, 'C', 1, 1),
       (4, 'A', 6, 2),
       (5, 'B', 1, 2),
       (6, 'C', 7, 1),
       (7, 'A', 6, 1),
       (8, 'B', 3, 2),
       (9, 'C', 6, 2),
       (10, 'A', 1, 1);


INSERT INTO proceds_and_medics (name, kind)
VALUES ('Adrenalin', 'Medicament'),
       ('Physiotherapy', 'Procedure'),
       ('Massage', 'Procedure'),
       ('Mechanotherapy', 'Procedure'),
       ('Aromatherapy', 'Procedure'),
       ('Music therapy', 'Procedure'),
       ('Reflexology', 'Procedure'),
       ('Diet therapy', 'Procedure'),
       ('Occupational therapy', 'Procedure'),
       ('Analgesic', 'Medicament'),
       ('Muscle relaxant', 'Medicament'),
       ('Corticosteroid', 'Medicament'),
       ('Antidepressant', 'Medicament'),
       ('Tranquilizer', 'Medicament'),
       ('Vitamins', 'Medicament');

-- INSERT INTO employees_roles (employee, role)
-- VALUES (1, 1),
--        (2, 1),
--        (3, 2),
--        (4, 2),
--        (5, 2),
--        (6, 2),
--        (7, 2),
--        (8, 2),
--        (9, 2),
--        (10, 4),
--        (11, 4),
--        (12, 4),
--        (13, 3);

INSERT INTO diagnoses (name, patient_card, comment)
VALUES ('diag1', 1, 'some comment'),
       ('diag2', 1, 'some comment2');


INSERT INTO diagnoses (name, patient_card)
VALUES ('diag4', 1),
       ('diag3', 2),
       ('diag5', 3),
       ('diag5', 4);

INSERT INTO "prescriptions" (patient, type, start_date, end_date, responsible_doctor)
VALUES (2, 2, '2018-08-31', '2019-08-14', 10),
       (2, 2, '2019-06-20', '2019-11-11', 10),
       (3, 2, '2019-05-29', '2020-02-27', 11),
       (4, 2, '2019-05-15', '2020-03-14', 11),
       (1, 2, '2019-05-22', '2019-09-08', 12);

INSERT INTO "prescriptions" (patient, type, start_date, end_date, responsible_doctor, dose)
VALUES (2, 1, '2019-01-29', '2019-08-02', 10, '2mg'),
       (1, 1, '2019-01-27', '2020-06-16', 11, '3mg'),
       (1, 1, '2019-07-06', '2020-03-22', 12, '4mg');

INSERT INTO "prescription_times" (prescription, time)
VALUES ('1', '08:11'),
       ('1', '20:33'),
       ('2', '18:31'),
       ('2', '10:11'),
       ('3', '03:25'),
       ('3', '14:01'),
       ('4', '08:39'),
       ('4', '05:20'),
       ('5', '17:34'),
       ('5', '04:48'),
       ('6', '00:53'),
       ('6', '13:22'),
       ('7', '01:23'),
       ('7', '07:29'),
       ('8', '18:55'),
       ('8', '16:32'),
       ('1', '16:50'),
       ('1', '17:27'),
       ('2', '20:31'),
       ('2', '12:09');

INSERT INTO events (patient, type, date, dose)
SELECT patient, type, (date(start_date) || ' ' || time)::timestamp, dose
FROM prescriptions
         INNER JOIN prescription_times ON prescriptions.prescription_id = prescription_times.prescription;








