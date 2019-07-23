package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.AccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class AccountServiceIImpl implements AccountService {
    @Autowired
    private PatientDao patientDao;

    @Override
    public List<Patient> getPatients() {
        return patientDao.getPatients();
    }

}
