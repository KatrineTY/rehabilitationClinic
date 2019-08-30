package com.javaschool.services.impls;

import com.javaschool.activemq.MessageSender;
import com.javaschool.dao.impls.EventDaoImpl;
import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.EventDto;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PatientCard;
import com.javaschool.entities.PrescriptionTime;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.EventService;
import com.javaschool.services.interfaces.PatientCardService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private int countOfEvents;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PatientCardService patientCardService;
    @Autowired
    private MessageSender messageSender;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addEvents(PrescriptionInfo prescriptionInfo) {
        List<Event> events = collectEvents(prescriptionInfo);
        events.forEach(event -> eventDao.addEvent(event));
        messageSender.send("add events");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteEvents(PrescriptionInfo prescriptionInfo) {
        eventDao.deleteEvents(prescriptionInfo);
        messageSender.send("delete events by prescription info");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteEvents(int patientId) {
        eventDao.deleteEvents(patientId);
        messageSender.send("delete events by id");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents() {
        return eventDao.getEvents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(int patientId) {
        return eventDao.getEvents(patientId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void takeTask(int id, String nurseName) {
        eventDao.updateEventStatus(id, employeeService.getEmployeeByName(nurseName), null, "In progress", LocalTime.now(), null);
        messageSender.send("take task");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void finishTask(int id, String nurseName) {
        eventDao.updateEventStatus(id, employeeService.getEmployeeByName(nurseName), null, "Finished", null, LocalTime.now());
        messageSender.send("finish task");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void rejectTask(int id, String nurseName, String comment) {
        eventDao.updateEventStatus(id, employeeService.getEmployeeByName(nurseName), comment, "Rejected", null, LocalTime.now());
        messageSender.send("reject task");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getEventsPage(int page) {
        return eventDao.getEventsPage(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public int getCountOfPages() {
        return (int) Math.ceil(((float) countOfEvents / EventDaoImpl.COUNT_OF_EVENTS_PER_PAGE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date, TimePeriodInfo timePeriodInfo) {
        log.debug("filters of filtering events for {} page: patient name - {}, date - {}, time period - {}"
                , page, patientName, date, timePeriodInfo);
        List<Event> events;
        int dbPage = page - 1;
        if (date != null && (patientName != null && !patientName.isEmpty())) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName, date, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> StringUtils.containsIgnoreCase(event.getPatient().getName(), patientName)
                            && event.getDate().toLocalDate().equals(date)
                            && isTimeInPeriod(timePeriodInfo, event))
                    .count();
        } else if (patientName != null && !patientName.isEmpty()) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> StringUtils.containsIgnoreCase(event.getPatient().getName(), patientName)
                            && isTimeInPeriod(timePeriodInfo, event))
                    .count();
        } else if (date != null) {
            events = eventDao.getFilteredEventsPage(dbPage, date, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> event.getDate().toLocalDate().equals(date)
                            && isTimeInPeriod(timePeriodInfo, event))
                    .count();
        } else if (!timePeriodInfo.getEndTime().equals(LocalTime.of(23, 59))
                || !timePeriodInfo.getStartTime().equals(LocalTime.of(0, 0))) {
            events = eventDao.getFilteredEventsPage(dbPage, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> isTimeInPeriod(timePeriodInfo, event))
                    .count();
        } else {
            events = getEventsPage(dbPage);
            countOfEvents = getEvents().size();
        }
        return events;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getEventDtosPerDay() {
        return eventDao.getEventsPerDay().stream()
                .map(event -> EventDto.builder()
                        .building(event.getBuilding())
                        .comment(event.getComment())
                        .date(event.getDate().toString())
                        .dose(event.getDose())
                        .nurseName(event.getNurse() == null ? "" : event.getNurse().getName())
                        .patientName(event.getPatient().getName())
                        .promedKind(event.getType().getKind())
                        .promedName(event.getType().getName())
                        .status(event.getStatus())
                        .ward(event.getWard())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(String nurseName) {
        return eventDao.getEvents(nurseName);
    }

    /**
     * Check if an event time is in a time period
     *
     * @param timePeriodInfo - the specified time period
     * @param event          - the event whose time will be checked
     * @return true if event's time in time period (include bounds), false otherwise
     */
    private boolean isTimeInPeriod(TimePeriodInfo timePeriodInfo, Event event) {
        return (event.getDate().toLocalTime().isBefore(timePeriodInfo.getEndTime())
                || event.getDate().toLocalTime().equals(timePeriodInfo.getEndTime()))
                && (event.getDate().toLocalTime().isAfter(timePeriodInfo.getStartTime())
                || event.getDate().toLocalTime().equals(timePeriodInfo.getStartTime()));
    }

    /**
     * Collect all events for a specific prescription period, days and times
     *
     * @param prescriptionInfo - the prescription info to be used to get all days adn times and period
     * @return list off events that should be created for a specific prescription
     */
    private List<Event> collectEvents(PrescriptionInfo prescriptionInfo) {
        List<LocalTime> times = getPrescriptionTimes(prescriptionInfo);
        List<String> prescriptionDays = prescriptionInfo.getPrescription().getPrescriptionDays();
        List<Event> events = new ArrayList<>();
        PatientCard patientCard = patientCardService.getPatientCard(prescriptionInfo.getPrescription().getPatient());
        for (LocalTime time : times) {
            for (LocalDate date = prescriptionInfo.getPrescription().getStartDate();
                 date.isBefore(prescriptionInfo.getPrescription().getEndDate());
                 date = date.plusDays(1)) {
                if (prescriptionDays.contains(date.getDayOfWeek().name())) {
                    Event event = new Event();
                    event.setPatient(prescriptionInfo.getPrescription().getPatient());
                    event.setType(prescriptionInfo.getPrescription().getType());
                    event.setDate(LocalDateTime.of(date, time));
                    event.setDose(prescriptionInfo.getPrescription().getDose());
                    event.setBuilding(patientCard.getBuilding());
                    event.setWard(patientCard.getWard());
                    events.add(event);
                }
            }
        }
        return events;
    }

    /**
     * Get prescription times from a prescription info
     *
     * @param prescriptionInfo - the specified prescription info
     * @return list of times of the prescription
     */
    private List<LocalTime> getPrescriptionTimes(PrescriptionInfo prescriptionInfo) {
        return prescriptionInfo.getPrescriptionTimes()
                .stream()
                .map(PrescriptionTime::getTime)
                .collect(Collectors.toList());
    }

}
