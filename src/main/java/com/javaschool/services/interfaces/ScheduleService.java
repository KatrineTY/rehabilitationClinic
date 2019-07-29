package com.javaschool.services.interfaces;

import com.javaschool.entities.Event;

import java.util.List;

public interface ScheduleService {

    List<Event> getScheduleByPatientId(int id);

}
