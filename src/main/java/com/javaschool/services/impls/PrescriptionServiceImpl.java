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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public PrescriptionInfo getPrescription(int id) {
        log.debug("get prescription info with prescription id: {}", id);
        PrescriptionInfo prescriptionInfo = new PrescriptionInfo();
        Prescription prescription = prescriptionDao.getPrescription(id);
        prescriptionInfo.setPrescription(prescription);
        prescriptionInfo.setPrescriptionTimes(prescriptionTimeService.getPrescriptionTimes(prescription));
        return prescriptionInfo;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Update prescription and prescription times
     *
     * @param prescriptionInfo - prescription info that contains specified prescription and prescription times
     */
    private void updatePrescription(PrescriptionInfo prescriptionInfo) {
        prescriptionDao.updatePrescription(prescriptionInfo.getPrescription());
        prescriptionTimeService.updatePrescriptionTimes(prescriptionInfo.getPrescriptionTimes());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        fillPrescription(prescriptionInfo, empName);
        fillPrescriptionTimes(prescriptionInfo);

        prescriptionDao.addPrescription(prescriptionInfo.getPrescription());
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescriptionTimeService.addPrescriptionTime(prescrTime));
        eventService.addEvents(prescriptionInfo);
    }

    /**
     * Collect prescription from prescription info and with employee name who uses prescription
     *
     * @param prescriptionInfo - the prescription info to collect full information about prescription
     * @param empName          - the name of an employee who uses prescription
     */
    private void fillPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        prescriptionInfo.getPrescription().setPatient(patientService.getPatient(
                prescriptionInfo.getPrescription().getPatient().getName()));
        prescriptionInfo.getPrescription().setType(procedureAndMedicamentService.getPromedWithId(
                prescriptionInfo.getPrescription().getType()));
        prescriptionInfo.getPrescription().setResponsibleDoctor(employeeService.getEmployeeByName(empName));
    }

    /**
     * Collect prescription times from prescription info
     *
     * @param prescriptionInfo - the prescription info to collect full information about prescription times
     */
    private void fillPrescriptionTimes(PrescriptionInfo prescriptionInfo) {
        Prescription prescription = prescriptionInfo.getPrescription();
        prescriptionInfo.getPrescriptionTimes().forEach(prescrTime -> prescrTime.setPrescription(prescription));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePrescriptionWithEvents(int id) {
        eventService.deleteEvents(getPrescription(id));
        prescriptionDao.deletePrescription(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePrescriptions(int patientId) {
        prescriptionDao.deletePrescriptions(patientId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prescription getLastPrescription(String patientName, ProcedureAndMedicament promed) {
        return prescriptionDao.getLastPrescription(patientName, promed);
    }

}
