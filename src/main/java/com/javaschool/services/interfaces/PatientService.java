package com.javaschool.services.interfaces;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Add information about specific patient into DB
     *
     * @param patientInfo - the patient info to be added into DB
     */
    void addPatient(PatientInfo patientInfo);

    /**
     * Retrieve patient by his name
     *
     * @param name - the name to be used to get patient
     * @return the specified patient
     */
    Patient getPatient(String name);

    /**
     * Retrieve patient by his id
     *
     * @param id - the id to be used to get patient
     * @return the specified patient
     */
    Patient getPatient(int id);

    /**
     * Update patient
     *
     * @param patient - the patient to be updated
     */
    void updatePatient(Patient patient);

    /**
     * Retrieve all patients
     *
     * @return list of patients
     */
    List<Patient> getPatients();

    /**
     * Check if an insurance already exists in the DB
     *
     * @param insurance - the insurance to be checked
     * @return true is the isurance already exists, false otherwise
     */
    boolean isInsuranceContains(String insurance);

}
