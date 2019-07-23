package com.javaschool.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int insurance;
}
