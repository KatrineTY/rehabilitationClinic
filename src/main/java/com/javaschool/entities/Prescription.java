package com.javaschool.entities;

import com.javaschool.converters.LocalDateAttributeConverter;
import com.javaschool.validation.constraints.DoseConstraint;
import com.javaschool.validation.constraints.PrescriptionForDischargedPatientConstraint;
import com.javaschool.validation.constraints.UniquePatientsPromedConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescriptions")
@DoseConstraint
@UniquePatientsPromedConstraint
@PrescriptionForDischargedPatientConstraint
public class Prescription {
    @Id
    @Column(name = "prescription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "patient", referencedColumnName = "patient_id")
    @Valid
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id")
    @Valid
    private ProcedureAndMedicament type;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Convert(converter = LocalDateAttributeConverter.class)
    @FutureOrPresent
    @NotNull
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Convert(converter = LocalDateAttributeConverter.class)
    @FutureOrPresent
    @NotNull
    private LocalDate endDate;
    @Column
    private String dose;
    @ManyToOne
    @JoinColumn(name = "responsible_doctor", referencedColumnName = "employee_id")
    private Employee responsibleDoctor;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE)
    private List<PrescriptionTime> prescriptionTimes;
    @ElementCollection(fetch = FetchType.EAGER)
    @Size(min = 1, message = "At least one day should be chosen")
    private List<String> prescriptionDays;

}
