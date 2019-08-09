package com.javaschool.entities;

import com.javaschool.validators.EditsGroup;
import com.javaschool.validators.PatientNameConstraint;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Audited
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Pattern(regexp = "[a-zA-Z ]+")
    @PatientNameConstraint(groups = {EditsGroup.class})
    private String name;
    @Column
    @Pattern(regexp = "[A-Z0-9 ]+")
    private String insurance;

}
