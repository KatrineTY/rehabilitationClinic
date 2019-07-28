package com.javaschool.dao.interfaces;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface DiagnosisDao {

    void addDiagnosis(Diagnosis diagnosis);

    List<Diagnosis> getDiagnosesByCard(PatientCard patientCard);

    void updateDiagnosis(Diagnosis diagnosis);

}
