package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;

public interface EventService {

    void addEventsForPrescription(PrescriptionInfo prescriptionInfo);

    void deleteEventsForPrescription(PrescriptionInfo prescriptionInfo);
}
