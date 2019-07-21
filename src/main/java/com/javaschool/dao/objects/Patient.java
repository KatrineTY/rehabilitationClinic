package com.javaschool.dao.objects;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String diagnosis;
    @Column
    private int insurance;
    @Column
    private String status;
    @OneToOne
    @JoinColumn(name = "attending_doctor")
//    @Column(name = "attending_doctor")
    private Employee attendingDoctor;
    @Column
    private String building;
    @Column
    private int ward;

}
