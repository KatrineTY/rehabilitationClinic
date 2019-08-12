package com.javaschool.services.interfaces;

import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface PatientCardService {

    List<PatientCard> getPatientCards();

    void addPatientCard(PatientCard patientCard);

    PatientCard getPatientCard(Patient patient);

    void updatePatientCard(PatientCard patientCard);

    void changeStatus(int patientId, String status);

    boolean isFreeBedInTheWard(String building, int ward);

}
