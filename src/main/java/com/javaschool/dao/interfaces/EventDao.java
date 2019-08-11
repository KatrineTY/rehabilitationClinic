package com.javaschool.dao.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;

import java.time.LocalDate;
import java.util.List;

public interface EventDao {

    List<Event> getEvents(Patient patient);

    List<Event> getEvents(int patientId);

    List<Event> getEvents(PrescriptionInfo prescriptionInfo);

    List<Event> getEvents();

    void addEvent(Event event);

    void deleteEvent(Event event);

    void deleteEvents(int patientId);

    void updateEventStatus(int id, Employee nurse, String comment, String status);

    List<Event> getEventsPage(int page);

    List<Event> getFilteredEventsPage(int page, LocalDate date);

    List<Event> getFilteredEventsPage(int page, String patientName);

    List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date);

}
