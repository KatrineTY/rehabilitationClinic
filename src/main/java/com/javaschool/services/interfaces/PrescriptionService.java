package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;

public interface PrescriptionService {

    void addPrescription(PrescriptionInfo prescriptionInfo, String empName);

}
