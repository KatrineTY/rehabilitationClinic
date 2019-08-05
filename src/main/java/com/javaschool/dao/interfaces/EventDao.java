package com.javaschool.dao.interfaces;

import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;

import java.util.List;

public interface EventDao {

    List<Event> getEvents(Patient patient);

    List<Event> getEvents(int patientId);

    List<Event> getEvents();

    void addEvent(Event event);

    void deleteEvent(Event event);

    void deleteEvents(int patientId);

    void updateEventStatus(int id, Employee nurse, String comment, String status);

}
