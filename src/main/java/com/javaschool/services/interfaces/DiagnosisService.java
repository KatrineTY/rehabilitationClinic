package com.javaschool.services.interfaces;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface DiagnosisService {

    /**
     * Add diagnosis into DB
     *
     * @param diagnosis - the diagnosis to be added
     */
    void addDiagnosis(Diagnosis diagnosis);

    /**
     * Retrieve diagnoses for the specific patient card
     *
     * @param patientCard - the patient card to be used to get diagnoses
     * @return - list of specified diagnoses
     */
    List<Diagnosis> getDiagnoses(PatientCard patientCard);

    /**
     * Add diagnosis
     *
     * @param diagnosis - the diagnosis to be added
     */
    void saveDiagnosis(Diagnosis diagnosis);

    /**
     * Delete diagnoses with specific patient card
     *
     * @param patientCard - the specified patient card
     */
    void deleteDiagnoses(PatientCard patientCard);

}
