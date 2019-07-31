package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.dao.interfaces.PrescriptionTimeDao;
import com.javaschool.dao.interfaces.ProcedureAndMedicamentDao;
import com.javaschool.dto.PrescriptionInfo;
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


    @Override
    public void addPrescription(PrescriptionInfo prescriptionInfo) {
        prescriptionInfo.getPrescription().setType(
                procedureAndMedicamentDao.getElementWithId(prescriptionInfo.getPrescription().getType()));
        prescriptionInfo.getPrescription().setPatient(
                patientDao.getPatientByName(prescriptionInfo.getPrescription().getPatient().getName()));
        prescriptionDao.addPrescription(prescriptionInfo.getPrescription());
        prescriptionInfo.getPrescriptionTimes().forEach(presTime -> presTime.setPrescription(prescriptionInfo.getPrescription()));
        prescriptionInfo.getPrescriptionTimes().forEach(presTime ->
                prescriptionTimeDao.addPrescriptionTime(presTime));
    }

}
