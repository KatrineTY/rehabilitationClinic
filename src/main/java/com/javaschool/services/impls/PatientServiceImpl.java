package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public void addPatient(Patient patient) {
        patient.setAttendingDoctor(employeeDao.getEmployeeByName(patient.getAttendingDoctor().getName()));
        patientDao.addPatient(patient);
    }

}
