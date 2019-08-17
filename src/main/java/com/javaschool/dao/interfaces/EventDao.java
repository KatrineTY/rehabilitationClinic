package com.javaschool.dao.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventDao {

    /**
     * Retrieve events for the specific patient
     *
     * @param patientId - the id of the specified patient
     * @return the list of specified events
     */
    List<Event> getEvents(int patientId);

    /**
     * Retrieve all events
     *
     * @return list of events
     */
    List<Event> getEvents();

    /**
     * Add events into DB
     *
     * @param event - the event that will be added into DB
     */
    void addEvent(Event event);

    /**
     * Delete events for the specific patient
     *
     * @param patientId - the id of the specified patient
     */
    void deleteEvents(int patientId);

    /**
     * Delete all events for the specific prescription info
     *
     * @param prescriptionInfo - the prescription info to be used to delete events
     */
    void deleteEvents(PrescriptionInfo prescriptionInfo);

    /**
     * Update event specific fields
     *
     * @param id      - id of the specified event
     * @param nurse   - the employee who updates event
     * @param comment - reason of rejecting event, can be empty
     * @param status  - the new status
     */
    void updateEventStatus(int id, Employee nurse, String comment, String status);

    /**
     * Retrieve events for a specific page of events
     *
     * @param page - the specified page
     * @return list of events for the specified page
     */
    List<Event> getEventsPage(int page);

    /**
     * Retrieve events for a specific page with filtering
     *
     * @param page           - the specified page
     * @param date           - date to be used to filter by date. Null if not specified
     * @param timePeriodInfo - time period to be used to filter by time period. Default (00:00-23:59) if not specified
     * @return filtered events for the specified page
     */
    List<Event> getFilteredEventsPage(int page, LocalDate date, TimePeriodInfo timePeriodInfo);

    /**
     * Retrieve events for a specific page with filtering
     *
     * @param page           - the specified page
     * @param patientName    - patient name to be used to filter by patient name. Null if not specified
     * @param timePeriodInfo - time period to be used to filter by time period. Default (00:00-23:59) if not specified
     * @return filtered events for the specified page
     */
    List<Event> getFilteredEventsPage(int page, String patientName, TimePeriodInfo timePeriodInfo);

    /**
     * Retrieve events for a specific page with filtering
     *
     * @param page           - the specified page
     * @param patientName    - patient name to be used to filter by patient name. Null if not specified
     * @param date           - date to be used to filter by date. Null if not specified
     * @param timePeriodInfo - time period to be used to filter by time period. Default (00:00-23:59) if not specified
     * @return filtered events for the specified page
     */
    List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date, TimePeriodInfo timePeriodInfo);

    /**
     * Retrieve events for a specific page with filtering
     *
     * @param timePeriodInfo - time period to be used to filter by time period. Default (00:00-23:59) if not specified
     * @return filtered events for the specified page
     */
    List<Event> getFilteredEventsPage(int dbPage, TimePeriodInfo timePeriodInfo);

}
