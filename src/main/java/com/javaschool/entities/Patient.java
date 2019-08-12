package com.javaschool.entities;

import com.javaschool.validation.constraints.PatientNameConstraint;
import com.javaschool.validation.constraints.UniqueInsuranceConstraint;
import com.javaschool.validation.groups.AddGroup;
import com.javaschool.validation.groups.EditGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Pattern(regexp = "[a-zA-Z ]+")
    @PatientNameConstraint(groups = {EditGroup.class})
    private String name;
    @Column
    @Pattern(regexp = "[A-Z0-9 ]+")
    @UniqueInsuranceConstraint(groups = {AddGroup.class})
    private String insurance;

}
