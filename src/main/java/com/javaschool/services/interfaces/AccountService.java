package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface AccountService {
    /**
     * Retrieve patient cards
     *
     * @return list of patient cards
     */
    List<PatientCard> getPatientCards();

    /**
     * Retrieve employee name by his login
     *
     * @param login - the login to be used for getting the name
     * @return the employee name
     */
    String getEmployeeNameByLogin(String login);

    /**
     * Retrieve all prescriptions
     *
     * @return list of prescriptions
     */
    List<PrescriptionInfo> getPrescriptions();

    /**
     * Retrieve all events
     *
     * @return list of events
     */
    List<Event> getEvents();

}
