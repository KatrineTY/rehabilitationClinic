package com.javaschool.services.interfaces;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface PatientManipulationService {

    void dischargePatient(int id);

    void updatePatientInfo(PatientInfo patientInfo);

    PatientInfo getPatientInfo(int patientId);

    List<PatientCard> getPatientCards();

    List<String> getAttendingDoctorNames();

    List<String> getResponsibleDoctorNames();

    void addPatient(PatientInfo patientInfo);

}
