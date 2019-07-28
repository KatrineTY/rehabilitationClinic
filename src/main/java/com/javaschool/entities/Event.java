package com.javaschool.entities;


import com.javaschool.converters.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient", referencedColumnName = "patient_id")
    private Patient patient;
    @Column(name = "date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    private ProcedureAndMedicament type;
    @ManyToOne
    @JoinColumn(name = "nurse", referencedColumnName = "employee_id")
    private Employee nurse;
    @Column
    private String comment;

}