package com.javaschool.entities;

import com.javaschool.converters.LocalTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "working_times")
public class WorkingTime {

    @Id
    @Column(name = "working_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "start_hours")
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime startHours;
    @Column(name = "end_hours")
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime endHours;
    @Column
    private boolean mon;
    @Column
    private boolean tue;
    @Column
    private boolean wen;
    @Column
    private boolean thu;
    @Column
    private boolean fri;
    @Column
    private boolean sat;
    @Column
    private boolean sun;

}
