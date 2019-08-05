package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    List<Event> getEvents(int patientId);

    void addEvents(PrescriptionInfo prescriptionInfo);

    void deleteEvents(PrescriptionInfo prescriptionInfo);

    void deleteEvents(int patientId);

    void takeTask(int id, String nurseName);

    void rejectTask(int id, String nurseName, String comment);

}
