package com.javaschool.entities;

import com.javaschool.validators.EditsGroup;
import com.javaschool.validators.PatientNameConstraint;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
    @NotBlank()
    @Pattern(regexp = "[a-zA-Z ]*")
    @PatientNameConstraint(groups = {EditsGroup.class})
    private String name;
    @Column
    @Digits(integer = 10, fraction = 0)
    private int insurance;

}
