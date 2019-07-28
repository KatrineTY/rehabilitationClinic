package com.javaschool.entities;


import com.javaschool.converters.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @Column(name = "prescription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient", referencedColumnName = "patient_id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    private ProcedureAndMedicament type;
    @Column(name = "start_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime startDate;
    @Column(name = "end_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime endDate;
    @Column
    private String dose;
    @Column
    private int version = 1;
    @ManyToOne
    @JoinColumn(name = "responsible_doctor", referencedColumnName = "employee_id")
    private Employee responsibleDoctor;

}
