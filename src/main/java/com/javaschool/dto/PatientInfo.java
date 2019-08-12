package com.javaschool.dto;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import com.javaschool.validation.constraints.DiagnosesListNamesConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {
    @Valid
    private Patient patient;
    @Valid
    private PatientCard patientCard;
    @DiagnosesListNamesConstraint
    private List<Diagnosis> diagnoses;

}
