package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PrescriptionTimeDao;
import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;
import com.javaschool.services.interfaces.PrescriptionTimeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class PrescriptionTimeServiceImpl implements PrescriptionTimeService {
    @Autowired
    private PrescriptionTimeDao prescriptionTimeDao;

    @Override
    public void addPrescriptionTime(PrescriptionTime prescriptionTime) {
        prescriptionTimeDao.addPrescriptionTime(prescriptionTime);
    }

    @Override
    public List<PrescriptionTime> getPrescriptionTimes(Prescription prescription) {
        return prescriptionTimeDao.getPrescriptionTimes(prescription);
    }

    @Override
    public void updatePrescriptionTimes(List<PrescriptionTime> prescriptionTimes) {
        prescriptionTimes.forEach(prescriptionTimeDao::updatePrescriptionTime);
    }

}
