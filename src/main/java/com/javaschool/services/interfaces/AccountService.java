package com.javaschool.services.interfaces;

import com.javaschool.dao.objects.Patient;

import java.util.List;

public interface AccountService {

    void addPatient(Patient patient);

    List<Patient> getPatients();

    Patient findPatientByName(String name);

}
