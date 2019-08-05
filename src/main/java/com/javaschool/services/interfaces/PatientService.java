package com.javaschool.services.interfaces;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.Patient;

public interface PatientService {

    void addPatient(PatientInfo patientInfo);

    Patient getPatient(String name);

    Patient getPatient(int id);

    void updatePatient(Patient patient);

}
