package com.javaschool.entities;


import com.javaschool.converters.LocalDateAttributeConverter;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Audited
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate endDate;
    @Column
    private String dose;
    @Column
    private int version = 1;
    @ManyToOne
    @JoinColumn(name = "responsible_doctor", referencedColumnName = "employee_id")
    private Employee responsibleDoctor;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE)
    private List<PrescriptionTime> prescriptionTimes;

}
