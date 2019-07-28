package com.javaschool.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "proceds_and_medics")
public class ProcedureAndMedicament {

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String kind;

}
