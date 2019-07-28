package com.javaschool.services.interfaces;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface PatientService {

    void addPatient(PatientInfo patientInfo);

    PatientInfo getPatientInfoByPatientId(int id);

    void updatePatientInfo(PatientInfo patientInfo);

    List<PatientCard> getPatientCards();

}
