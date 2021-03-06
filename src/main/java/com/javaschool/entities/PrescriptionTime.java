package com.javaschool.entities;

import com.javaschool.converters.LocalTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescription_times")
public class PrescriptionTime {
    @Id
    @Column(name = "prescription_time_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "prescription", referencedColumnName = "prescription_id")
    private Prescription prescription;
    @Column(name = "time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Convert(converter = LocalTimeAttributeConverter.class)
    private LocalTime time;

}
