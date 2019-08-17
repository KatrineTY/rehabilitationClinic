package com.javaschool.dao.interfaces;

import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface DiagnosisDao {
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
     * Save new of update existed diagnosis
     *
     * @param diagnosis - the diagnosis to be updated/added
     */
    void saveOrUpdateDiagnosis(Diagnosis diagnosis);

}
