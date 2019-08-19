package com.javaschool.services.interfaces;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;

import java.util.List;

public interface PrescriptionTimeService {

    /**
     * Add prescription time into DB
     *
     * @param prescriptionTime - the prescription time to be updated
     */
    void addPrescriptionTime(PrescriptionTime prescriptionTime);

    /**
     * Retrieve prescription times for a specific prescription
     *
     * @param prescription - the specified prescription
     * @return list of the specified prescription times
     */
    List<PrescriptionTime> getPrescriptionTimes(Prescription prescription);

    /**
     * Update prescription times
     *
     * @param prescriptionTimes - prescription times that will be updated
     */
    void updatePrescriptionTimes(List<PrescriptionTime> prescriptionTimes);

    /**
     * Delete prescription time
     *
     * @param prescriptionTime - prescription time that will be deleted
     */
    void deletePrescriptionTime(PrescriptionTime prescriptionTime);

}
