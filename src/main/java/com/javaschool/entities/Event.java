package com.javaschool.entities;


import com.javaschool.converters.LocalDateTimeAttributeConverter;
import com.javaschool.converters.LocalTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date;
    @Column
    private String status = "Planned";
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    private ProcedureAndMedicament type;
    @ManyToOne
    @JoinColumn(name = "nurse", referencedColumnName = "employee_id")
    private Employee nurse;
    @Column
    private String comment;
    @Column
    private String dose;
    @Column
    private String building;
    @Column
    private int ward;
    @Column(name = "start_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime startTaskTime;
    @Column(name = "end_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime endTaskTime;

}
