package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.DiagnosisDao;
import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.dao.interfaces.PatientCardDao;
import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.PatientCard;
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
    private EmployeeDao employeeDao;
    @Autowired
    private DiagnosisDao diagnosisDao;
    @Autowired
    private PatientCardDao patientCardDao;

    @Override
    @Transactional
    public void addPatient(PatientInfo patientInfo) {
        patientDao.addPatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setPatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setAttendingDoctor(
                employeeDao.getEmployeeByName(patientInfo.getPatientCard().getAttendingDoctor().getName()));
        patientCardDao.addPatientCard(patientInfo.getPatientCard());
        patientInfo.getDiagnoses().forEach(diag -> diag.setPatientCard(patientInfo.getPatientCard()));
        patientInfo.getDiagnoses().forEach(diag -> diagnosisDao.addDiagnosis(diag));
    }

    @Override
    public PatientInfo getPatientInfoByPatientId(int id) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setPatient(patientDao.getPatientById(id));
        patientInfo.setPatientCard(patientCardDao.getPatientCardByPatient(patientInfo.getPatient()));
        patientInfo.setDiagnoses(diagnosisDao.getDiagnosesByCard(patientInfo.getPatientCard()));
        return patientInfo;
    }

    @Override
    public void updatePatientInfo(PatientInfo patientInfo) {
        patientDao.updatePatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setAttendingDoctor(
                employeeDao.getEmployeeByName(patientInfo.getPatientCard().getAttendingDoctor().getName()));
        patientInfo.getPatientCard().setPatient(patientInfo.getPatient());
        patientCardDao.updatePatientCard(patientInfo.getPatientCard());
        patientInfo.getDiagnoses().forEach(diag -> diag.setPatientCard(patientInfo.getPatientCard()));
        patientInfo.getDiagnoses().forEach(diag -> diagnosisDao.updateDiagnosis(diag));
    }

    @Override
    public List<PatientCard> getPatientCards() {
        return patientCardDao.getPatientCards();
    }


}
