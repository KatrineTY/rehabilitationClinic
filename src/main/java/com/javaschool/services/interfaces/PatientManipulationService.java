package com.javaschool.services.interfaces;

import com.javaschool.dto.PatientInfo;

import java.util.List;

public interface PatientManipulationService {

    /**
     * Discharge patient from clinic
     *
     * @param id - id of the specified patient
     */
    void dischargePatient(int id);

    /**
     * Update a specific patient info
     *
     * @param patientInfo - the patient info to be updated
     */
    void updatePatientInfo(PatientInfo patientInfo);

    /**
     * Collect patient info for a specific patient
     *
     * @param patientId - id of the specified patient
     * @return collected patient info
     */
    PatientInfo getPatientInfo(int patientId);

    /**
     * Retrieve all attending doctors
     *
     * @return list of attending doctors
     */
    List<String> getAttendingDoctorNames();

    /**
     * Retrieve all responsible doctors
     *
     * @return list of responsible doctors
     */
    List<String> getResponsibleDoctorNames();

}
