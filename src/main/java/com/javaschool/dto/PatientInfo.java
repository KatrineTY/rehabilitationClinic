package com.javaschool.dto;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
@Getter
@Setter
public class PatientInfo {
    @Valid
    private Patient patient;
    @Valid
    private PatientCard patientCard;
    @Valid
    private List<Diagnosis> diagnoses;

}
