package com.javaschool.dao.interfaces;

import com.javaschool.dao.objects.Patient;

import java.util.List;

public interface PatientDao {
    List<Patient> getPatients();

    void addPatient(Patient patient);

    Patient getPatientByName(String name);

}
