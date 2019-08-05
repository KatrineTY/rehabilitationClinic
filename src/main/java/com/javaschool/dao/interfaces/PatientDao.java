package com.javaschool.dao.interfaces;

import com.javaschool.entities.Patient;

import java.util.List;

public interface PatientDao {

    List<Patient> getPatients();

    void addPatient(Patient patient);

    Patient getPatient(String name);

    Patient getPatient(int id);

    void updatePatient(Patient patient);

}
