package com.javaschool.services.interfaces;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;

import java.util.List;

public interface PrescriptionTimeService {

    void addPrescriptionTime(PrescriptionTime prescriptionTime);

    List<PrescriptionTime> getPrescriptionTimes(Prescription prescription);

    void updatePrescriptionTimes(List<PrescriptionTime> prescriptionTimes);

}
