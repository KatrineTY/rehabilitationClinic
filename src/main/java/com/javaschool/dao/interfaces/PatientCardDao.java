package com.javaschool.dao.interfaces;

import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface PatientCardDao {
    /**
     * Add patient card into DB
     *
     * @param patientCard - the patient card to be added
     */
    void addPatientCard(PatientCard patientCard);

    /**
     * Retrieve patient cards
     *
     * @return list of patient cards
     */
    List<PatientCard> getPatientCards();

    /**
     * Retrieve a specific patient card
     *
     * @param patient - the patient to be used to get his patient card
     * @return the specified patient card
     */
    PatientCard getPatientCard(Patient patient);

    /**
     * Update data of a specific patient card
     *
     * @param patientCard - the patient card to be updated
     */
    void updatePatientCard(PatientCard patientCard);

    /**
     * Change status of a specific patient
     *
     * @param patientId - id number of the specified patient
     * @param status    - the new status of the patient
     */
    void changeStatus(int patientId, String status);

    /**
     * Check if a specific building has any free beds in a specific ward
     *
     * @param building - the specified building
     * @param ward     - the specified ward
     * @return - true if there are free beds, false otherwise
     */
    boolean isFreeBedInTheWard(String building, int ward);

}
