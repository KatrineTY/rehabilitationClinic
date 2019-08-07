package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.DiagnosisServise;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.PatientCardService;
import com.javaschool.services.interfaces.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DiagnosisServise diagnosisService;
    @Autowired
    private PatientCardService patientCardService;

    @Override
    public void addPatient(PatientInfo patientInfo) {
        patientDao.addPatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setPatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setAttendingDoctor(
                employeeService.getEmployeeByName(patientInfo.getPatientCard().getAttendingDoctor().getName()));
        patientCardService.addPatientCard(patientInfo.getPatientCard());
        patientInfo.getDiagnoses().forEach(diag -> diag.setPatientCard(patientInfo.getPatientCard()));
        patientInfo.getDiagnoses().forEach(diag -> diagnosisService.addDiagnosis(diag));
    }

    @Override
    public Patient getPatient(String name) {
        return patientDao.getPatient(name);
    }

    @Override
    public Patient getPatient(int id) {
        return patientDao.getPatient(id);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientDao.updatePatient(patient);
    }

    @Override
    public List<Patient> getPatients() {
        return patientDao.getPatients();
    }

}
