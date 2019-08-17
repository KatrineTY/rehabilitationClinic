package com.javaschool.dao.interfaces;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface PrescriptionDao {
    /**
     * Add prescription DB
     *
     * @param prescription - the prescription to be added into DB
     */
    void addPrescription(Prescription prescription);

    /**
     * Retrieve all prescriptions
     *
     * @return list of prescriptions
     */
    List<Prescription> getPrescriptions();

    /**
     * Retrieve prescriptions with specific id
     *
     * @param id - the id to be used to get prescription
     * @return specified prescription
     */
    Prescription getPrescription(int id);

    /**
     * Update prescription
     *
     * @param prescription - the new prescription
     */
    void updatePrescription(Prescription prescription);

    /**
     * Delete prescription and ins "Planned" events
     *
     * @param id - id of the specified prescription
     */
    void deletePrescription(int id);

    /**
     * Delete prescription with specific patient
     *
     * @param patientId - id of the specified patients
     */
    void deletePrescriptions(int patientId);

    /**
     * Retrieve last added prescription with specific procedure or medicament for specific patient
     *
     * @param patientName - name of the specified patient
     * @param promed      - the specified procedure or medicament
     * @return prescription that matches specified conditions
     */
    Prescription getLastPrescription(String patientName, ProcedureAndMedicament promed);

}
