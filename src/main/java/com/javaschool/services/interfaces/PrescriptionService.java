package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;

import java.util.List;

public interface PrescriptionService {

    void addPrescription(PrescriptionInfo prescriptionInfo, String empName);

    List<PrescriptionInfo> getAllPrescriptions();

    PrescriptionInfo getPrescriptionById(int id);

    void updatePrescriptionInfo(PrescriptionInfo prescriptionInfo, String empName);

    void deletePrescriptionById(int id);

    void deletePrescriptionsByPatientId(int id);
}
