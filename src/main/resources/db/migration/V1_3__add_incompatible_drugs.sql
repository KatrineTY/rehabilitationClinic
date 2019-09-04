CREATE TABLE incompatible_promeds
(
    incompatible_promed_id SERIAL PRIMARY KEY,
    promed                 INTEGER NOT NULL REFERENCES proceds_and_medics (type_id),
    incomp_promed          INTEGER NOT NULL REFERENCES proceds_and_medics (type_id)
);

CREATE INDEX imcomp_promed_fk_index ON incompatible_promeds (promed);

INSERT INTO incompatible_promeds (promed, incomp_promed)
VALUES (1, 2),
       (5, 4),
       (1, 3);



