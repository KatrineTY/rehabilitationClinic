package com.javaschool.dao.interfaces;

import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface PatientCardDao {
    void addPatientCard(PatientCard patientCard);

    List<PatientCard> getPatientCards();

    PatientCard getPatientCardByPatient(Patient patient);

    void updatePatientCard(PatientCard patientCard);

    void dischargePatientByPatientId(int id);

}
