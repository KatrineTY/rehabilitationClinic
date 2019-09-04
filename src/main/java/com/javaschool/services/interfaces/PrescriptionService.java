package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Prescription;
import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface PrescriptionService {

    /**
     * Retrieve all prescriptions
     *
     * @return list of prescriptions
     */
    List<PrescriptionInfo> getPrescriptions();

    /**
     * Retrieve prescriptions with specific id
     *
     * @param id - the id to be used to get prescription
     * @return specified prescription
     */
    PrescriptionInfo getPrescription(int id);

    /**
     * Update prescription information
     *
     * @param prescriptionInfo - the new prescription information
     * @param empName          - the name of an employee who changes prescription
     */
    void updatePrescriptionInfo(PrescriptionInfo prescriptionInfo, String empName);

    /**
     * Add prescription into DB
     *
     * @param prescriptionInfo - the prescription information to be added into DB
     * @param empName          - the name of an employee who adds prescription
     */
    void addPrescription(PrescriptionInfo prescriptionInfo, String empName);

    /**
     * Delete prescription and its "Planned" events
     *
     * @param id - id of the specified prescription
     */
    void deletePrescriptionWithEvents(int id);

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

    /**
     * Retrieve prescriptions for a specific patient
     *
     * @param patientName - the specified patient name
     * @return list of prescriptions for the specified patient
     */
    List<Prescription> getPrescriptions(String patientName);
}
