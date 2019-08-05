package com.javaschool.services.interfaces;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface DiagnosisServise {

    void addDiagnosis(Diagnosis diagnosis);

    List<Diagnosis> getDiagnoses(PatientCard patientCard);

    void updateDiagnosis(Diagnosis diagnosis);

}
