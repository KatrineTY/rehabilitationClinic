package com.javaschool.entities;

import com.javaschool.converters.LocalTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "prescription_times")
public class PrescriptionTime {

    @Id
    @Column(name = "prescription_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "prescription", referencedColumnName = "prescription_id")
    private Prescription prescription;
    @Column(name = "time")
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime time;

}
