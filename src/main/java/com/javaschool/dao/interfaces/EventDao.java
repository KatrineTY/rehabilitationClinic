package com.javaschool.dao.interfaces;

import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;

import java.util.List;

public interface EventDao {

    List<Event> getEventsByPatient(Patient patient);

    void addEvent(Event event);
}
