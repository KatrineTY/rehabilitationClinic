package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.*;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Prescription;
import com.javaschool.services.interfaces.EventService;
import com.javaschool.services.interfaces.PrescriptionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionDao prescriptionDao;
    @Autowired
    private PrescriptionTimeDao prescriptionTimeDao;
    @Autowired
    private ProcedureAndMedicamentDao procedureAndMedicamentDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EventService eventService;

    @Override
    public List<PrescriptionInfo> getAllPrescriptions() {
        return prescriptionDao.getAllPrescriptions()
                .stream()
                .map(prescr -> {
                    PrescriptionInfo prescriptionInfo = new PrescriptionInfo();
                    prescriptionInfo.setPrescriptionTimes(prescriptionTimeDao
                            .getPrescriptionTimesByPrescription(prescr));
                    prescriptionInfo.setPrescription(prescr);
                    return prescriptionInfo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionInfo getPrescriptionById(int id) {
        PrescriptionInfo prescriptionInfo = new PrescriptionInfo();
        Prescription prescription = prescriptionDao.getPrescriptionById(id);
        prescriptionInfo.setPrescription(prescription);
        prescriptionInfo.setPrescriptionTimes(prescriptionTimeDao.getPrescriptionTimesByPrescription(prescription));
        return prescriptionInfo;
    }

    @Override
    public void updatePrescriptionInfo(PrescriptionInfo prescriptionInfo, String empName) {
        fillPrescription(prescriptionInfo, empName);
        fillPrescriptionTimes(prescriptionInfo);
        PrescriptionInfo oldPrescriptionInfo = getPrescriptionById(prescriptionInfo.getPrescription().getId());
        eventService.deleteEventsForPrescription(oldPrescriptionInfo);

        updatePrescription(prescriptionInfo);
        eventService.addEventsForPrescription(prescriptionInfo);
    }

    private void updatePrescription(PrescriptionInfo prescriptionInfo) {
        prescriptionDao.updatePrescription(prescriptionInfo.getPrescription());
        prescriptionTimeDao.updatePrescriptionTimes(prescriptionInfo.getPrescriptionTimes());
    }

    @Override
    public void addPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        fillPrescription(prescriptionInfo, empName);
        fillPrescriptionTimes(prescriptionInfo);

        prescriptionDao.addPrescription(prescriptionInfo.getPrescription());
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescriptionTimeDao.addPrescriptionTime(prescrTime));
        eventService.addEventsForPrescription(prescriptionInfo);
    }

    private void fillPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        prescriptionInfo.getPrescription().setPatient(patientDao.getPatientByName(
                prescriptionInfo.getPrescription().getPatient().getName()));
        prescriptionInfo.getPrescription().setType(procedureAndMedicamentDao.getElementWithId(
                prescriptionInfo.getPrescription().getType()));
        prescriptionInfo.getPrescription().setResponsibleDoctor(employeeDao.getEmployeeByName(empName));
    }

    private void fillPrescriptionTimes(PrescriptionInfo prescriptionInfo) {
        Prescription prescription = prescriptionInfo.getPrescription();
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescrTime.setPrescription(prescription));
    }

    @Override
    public void deletePrescriptionById(int id) {
        eventService.deleteEventsForPrescription(getPrescriptionById(id));
        prescriptionTimeDao.deletePrescriptionTimesByPrescriptionId(id);
        prescriptionDao.deletePrescriptionById(id);
    }

    @Override
    public void deletePrescriptionsByPatientId(int id) {
        List<Integer> ids = prescriptionDao.getPrescriptionsIdByPatientId(id);
        ids.forEach(prescriptionTimeDao::deletePrescriptionTimesByPrescriptionId);
        prescriptionDao.deletePrescriptionsByPatientId(id);
    }

}
