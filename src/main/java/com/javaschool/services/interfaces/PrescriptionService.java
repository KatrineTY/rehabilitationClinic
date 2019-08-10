package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;

import java.util.List;

public interface PrescriptionService {

    void addPrescription(PrescriptionInfo prescriptionInfo, String empName);

    List<PrescriptionInfo> getPrescriptions();

    PrescriptionInfo getPrescription(int id);

    void updatePrescriptionInfo(PrescriptionInfo prescriptionInfo, String empName);

    void deletePrescriptionWithEvents(int id);

    void deletePrescriptions(int patientId);

}
