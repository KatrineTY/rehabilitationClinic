package com.javaschool.dao.interfaces;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;

import java.util.List;

public interface PrescriptionTimeDao {

    void addPrescriptionTime(PrescriptionTime prescriptionTime);

    List<PrescriptionTime> getPrescriptionTimes(Prescription prescription);

    void updatePrescriptionTime(PrescriptionTime prescriptionTime);

}
