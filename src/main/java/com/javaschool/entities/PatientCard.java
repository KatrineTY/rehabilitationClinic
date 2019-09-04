package com.javaschool.entities;

import com.javaschool.validation.constraints.PatientCountInWardConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient_cards")
@PatientCountInWardConstraint
public class PatientCard {
    @Id
    @Column(name = "patient_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "patient", referencedColumnName = "patient_id")
    private Patient patient;
    @Column
    private String status = "Treated";
    @ManyToOne
    @JoinColumn(name = "attending_doctor", referencedColumnName = "employee_id")
    private Employee attendingDoctor;
    @Column
    private String building;
    @Column
    private int ward;

}
