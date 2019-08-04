package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;

import java.util.List;

public interface EventService {

    void addEventsForPrescription(PrescriptionInfo prescriptionInfo);

    void deleteEventsForPrescription(PrescriptionInfo prescriptionInfo);

    void deleteEventsByPatientId(int id);

    List<Event> getEvents();

    void takeTask(int id, String nurseName);

    void rejectTask(int id, String nurseName, String comment);
}
