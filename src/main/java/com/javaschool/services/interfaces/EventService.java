package com.javaschool.services.interfaces;

import com.javaschool.dto.EventDto;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    /**
     * Add all events for the specific prescription info
     *
     * @param prescriptionInfo - the prescription info to be used to add events
     */
    void addEvents(PrescriptionInfo prescriptionInfo);

    /**
     * Delete all events for the specific prescription info
     *
     * @param prescriptionInfo - the prescription info to be used to delete events
     */
    void deleteEvents(PrescriptionInfo prescriptionInfo);

    /**
     * Delete events for the specific patient
     *
     * @param patientId - the id of the specified patient
     */
    void deleteEvents(int patientId);

    /**
     * Change status of an event to 'In progress'
     *
     * @param id        -  the id of the specified event
     * @param nurseName - the name of an employee who changes event status
     */
    void takeTask(int id, String nurseName);

    /**
     * Change status of an event to 'Rejected'
     *
     * @param id        - the id of the specified event
     * @param nurseName - the name of an employee who changes event status
     * @param comment   - comment of rejected task
     */
    void rejectTask(int id, String nurseName, String comment);

    /**
     * Retrieve events for a specific page of events
     *
     * @param page - the specified page
     * @return list of events for the specified page
     */
    List<Event> getEventsPage(int page);

    /**
     * Retrieve all events
     *
     * @return list of events
     */
    List<Event> getEvents();

    /**
     * Retrieve events for the specific patient
     *
     * @param patientId - the id of the specified patient
     * @return the list of specified events
     */
    List<Event> getEvents(int patientId);

    /**
     * Retrieve count of pages at the current time
     *
     * @return count of pages
     */
    int getCountOfPages();

    /**
     * Retrieve events for a specific page with filtering if filters are specified
     *
     * @param page           - the specified page
     * @param patientName    - patient name to be used to filter by patient name. Null if not specified
     * @param date           - date to be used to filter by date. Null if not specified
     * @param timePeriodInfo - time period to be used to filter by time period. Default (00:00-23:59) if not specified
     * @return filtered events for the specified page
     */
    List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date, TimePeriodInfo timePeriodInfo);

    /**
     * Retrieve events one day ahead
     *
     * @return specified events
     */
    List<EventDto> getEventDtosPerDay();

}
