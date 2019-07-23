package com.javaschool.entities;

import com.javaschool.converters.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @Column(name = "diagnosis_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "patient_card")
    private PatientCard patientCard;
    @Column(name = "start_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime startDate;
    @Column(name = "end_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime endDate;
    @Column
    private String comment;

}
