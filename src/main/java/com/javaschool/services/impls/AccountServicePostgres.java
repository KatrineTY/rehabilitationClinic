package com.javaschool.services.impls;

import com.javaschool.dao.objects.Patient;
import com.javaschool.services.interfaces.AccountService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountServicePostgres implements AccountService {

    @Override
    public void addPatient(Patient patient) {

    }

    @Override
    public List<Patient> getPatients() {
        return null;
    }

    @Override
    public Patient findPatientByName(String name) {
        return null;
    }

}
