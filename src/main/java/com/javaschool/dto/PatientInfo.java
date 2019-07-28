package com.javaschool.dto;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class PatientInfo {
    private Patient patient;
    private PatientCard patientCard;
    private List<Diagnosis> diagnoses;

}
