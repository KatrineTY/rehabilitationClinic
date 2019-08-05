package com.javaschool.entities;

import com.javaschool.converters.LocalDateTimeAttributeConverter;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Audited
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @Column(name = "diagnosis_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "patient_card", referencedColumnName = "patient_card_id")
    private PatientCard patientCard;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime startDate = LocalDateTime.now();
    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime endDate;
    @Column
    private String comment;

}
