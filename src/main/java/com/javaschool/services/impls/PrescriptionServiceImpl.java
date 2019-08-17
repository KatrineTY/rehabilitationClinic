package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Prescription;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Transactional
@Slf4j
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionDao prescriptionDao;
    @Autowired
    private PrescriptionTimeService prescriptionTimeService;
    @Autowired
    private ProcedureAndMedicamentService procedureAndMedicamentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EventService eventService;

    @Override
    public List<PrescriptionInfo> getPrescriptions() {
        return prescriptionDao.getPrescriptions()
                .stream()
                .map(prescr -> {
                    PrescriptionInfo prescriptionInfo = new PrescriptionInfo();
                    prescriptionInfo.setPrescriptionTimes(prescriptionTimeService
                            .getPrescriptionTimes(prescr));
                    prescriptionInfo.setPrescription(prescr);
                    return prescriptionInfo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionInfo getPrescription(int id) {
        log.debug("get prescription info with prescription id: {}", id);
        PrescriptionInfo prescriptionInfo = new PrescriptionInfo();
        Prescription prescription = prescriptionDao.getPrescription(id);
        prescriptionInfo.setPrescription(prescription);
        prescriptionInfo.setPrescriptionTimes(prescriptionTimeService.getPrescriptionTimes(prescription));
        return prescriptionInfo;
    }

    @Override
    public void updatePrescriptionInfo(PrescriptionInfo prescriptionInfo, String empName) {
        PrescriptionInfo oldPrescriptionInfo = getPrescription(prescriptionInfo.getPrescription().getId());
        eventService.deleteEvents(oldPrescriptionInfo);
        fillPrescription(prescriptionInfo, empName);
        fillPrescriptionTimes(prescriptionInfo);

        updatePrescription(prescriptionInfo);
        eventService.addEvents(prescriptionInfo);
        log.debug("updated prescription info: {}", prescriptionInfo);
    }

    private void updatePrescription(PrescriptionInfo prescriptionInfo) {
        prescriptionDao.updatePrescription(prescriptionInfo.getPrescription());
        prescriptionTimeService.updatePrescriptionTimes(prescriptionInfo.getPrescriptionTimes());
    }

    @Override
    public void addPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        fillPrescription(prescriptionInfo, empName);
        fillPrescriptionTimes(prescriptionInfo);

        prescriptionDao.addPrescription(prescriptionInfo.getPrescription());
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescriptionTimeService.addPrescriptionTime(prescrTime));
        eventService.addEvents(prescriptionInfo);
    }

    private void fillPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        prescriptionInfo.getPrescription().setPatient(patientService.getPatient(
                prescriptionInfo.getPrescription().getPatient().getName()));
        prescriptionInfo.getPrescription().setType(procedureAndMedicamentService.getElementWithId(
                prescriptionInfo.getPrescription().getType()));
        prescriptionInfo.getPrescription().setResponsibleDoctor(employeeService.getEmployeeByName(empName));
    }

    private void fillPrescriptionTimes(PrescriptionInfo prescriptionInfo) {
        Prescription prescription = prescriptionInfo.getPrescription();
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescrTime.setPrescription(prescription));
    }

    @Override
    public void deletePrescriptionWithEvents(int id) {
        eventService.deleteEvents(getPrescription(id));
        prescriptionDao.deletePrescription(id);
    }

    @Override
    public void deletePrescriptions(int patientId) {
        prescriptionDao.deletePrescriptions(patientId);
    }

    @Override
    public Prescription getLastPrescription(String patientName, ProcedureAndMedicament promed) {
        return prescriptionDao.getLastPrescription(patientName, promed);
    }

}
