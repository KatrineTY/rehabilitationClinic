package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.*;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.EventService;
import com.javaschool.services.interfaces.PrescriptionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public void addPrescription(PrescriptionInfo prescriptionInfo, String empName) {
        prescriptionInfo.getPrescription().setType(
                procedureAndMedicamentDao.getElementWithId(prescriptionInfo.getPrescription().getType()));
        prescriptionInfo.getPrescription().setResponsibleDoctor(employeeDao.getEmployeeByName(empName));
        prescriptionInfo.getPrescription().setPatient(
                patientDao.getPatientByName(prescriptionInfo.getPrescription().getPatient().getName()));
        prescriptionDao.addPrescription(prescriptionInfo.getPrescription());
        prescriptionInfo.getPrescriptionTimes().forEach(presTime -> presTime.setPrescription(prescriptionInfo.getPrescription()));
        prescriptionInfo.getPrescriptionTimes().forEach(presTime ->
                prescriptionTimeDao.addPrescriptionTime(presTime));
        eventService.addEventsForPrescription(prescriptionInfo);
    }

}
