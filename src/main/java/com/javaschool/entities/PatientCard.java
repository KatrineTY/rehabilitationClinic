package com.javaschool.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient_cards")
public class PatientCard {
    @Id
    @Column(name = "patient_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient", referencedColumnName = "patient_id")
    private Patient patient;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "attending_doctor", referencedColumnName = "employee_id")
    private Employee attendingDoctor;
    @Column
    private String building;
    @Column
    private int ward;

}
