package com.javaschool.dao.interfaces;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;

import java.util.List;

public interface PrescriptionTimeDao {

    void addPrescriptionTime(PrescriptionTime prescriptionTime);

    List<PrescriptionTime> getPrescriptionTimesByPrescription(Prescription prescription);

    void updatePrescriptionTimes(List<PrescriptionTime> prescriptionTimes);

}
