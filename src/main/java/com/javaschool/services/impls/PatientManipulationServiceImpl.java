package com.javaschool.services.impls;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class PatientManipulationServiceImpl implements PatientManipulationService {
    @Autowired
    private PatientCardService patientCardService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private EventService eventService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DiagnosisServise diagnosisService;

    @Override
    public PatientInfo getPatientInfo(int patientId) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setPatient(patientService.getPatient(patientId));
        patientInfo.setPatientCard(patientCardService.getPatientCard(patientInfo.getPatient()));
        patientInfo.setDiagnoses(diagnosisService.getDiagnoses(patientInfo.getPatientCard()));
        return patientInfo;
    }

    @Override
    public void updatePatientInfo(PatientInfo patientInfo) {
        patientService.updatePatient(patientInfo.getPatient());
        patientInfo.getPatientCard().setAttendingDoctor(
                employeeService.getEmployeeByName(patientInfo.getPatientCard().getAttendingDoctor().getName()));
        patientInfo.getPatientCard().setPatient(patientInfo.getPatient());
        patientCardService.updatePatientCard(patientInfo.getPatientCard());
        patientInfo.getDiagnoses().forEach(diag -> diag.setPatientCard(patientInfo.getPatientCard()));
        patientInfo.getDiagnoses().forEach(diag -> diagnosisService.updateDiagnosis(diag));
    }

    @Override
    public List<PatientCard> getPatientCards() {
        return patientCardService.getPatientCards();
    }

    @Override
    public void dischargePatient(int id) {
        patientCardService.changeStatus(id, "Discharged");
        eventService.deleteEvents(id);
        prescriptionService.deletePrescriptions(id);
    }

}
