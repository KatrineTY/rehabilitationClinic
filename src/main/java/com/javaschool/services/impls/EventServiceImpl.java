package com.javaschool.services.impls;

import com.javaschool.dao.impls.EventDaoImpl;
import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PrescriptionTime;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
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
@Transactional
public class EventServiceImpl implements EventService {
    private int countOfEvents;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void addEvents(PrescriptionInfo prescriptionInfo) {
        List<LocalTime> times = getPrescriptionTimes(prescriptionInfo);
        List<Event> events = collectEvents(prescriptionInfo, times);
        events.forEach(event -> eventDao.addEvent(event));
    }

    @Override
    public void deleteEvents(PrescriptionInfo prescriptionInfo) {
        List<Event> events = eventDao.getEvents(prescriptionInfo);
        events.forEach(event -> eventDao.deleteEvent(event));
    }

    @Override
    public void deleteEvents(int patientId) {
        eventDao.deleteEvents(patientId);
    }

    @Override
    public List<Event> getEvents() {
        return eventDao.getEvents();
    }

    @Override
    public List<Event> getEvents(int patientId) {
        return eventDao.getEvents(patientId);
    }

    @Override
    public void takeTask(int id, String nurseName) {
        eventDao.updateEventStatus(id, employeeService.getEmployeeByName(nurseName), null, "In progress");
    }

    @Override
    public void rejectTask(int id, String nurseName, String comment) {
        eventDao.updateEventStatus(id, employeeService.getEmployeeByName(nurseName), comment, "Rejected");
    }

    @Override
    public List<Event> getEventsPage(int page) {
        return eventDao.getEventsPage(page);
    }

    @Override
    public int getCountOfPages() {
        return (int) Math.ceil(((float) countOfEvents / EventDaoImpl.COUNT_OF_EVENTS_PER_PAGE));
    }

    @Override
    public List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date, TimePeriodInfo timePeriodInfo) {
        List<Event> events;
        int dbPage = page - 1;
        if (date != null && (patientName != null && !patientName.isEmpty())) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName, date, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> StringUtils.containsIgnoreCase(event.getPatient().getName(), patientName)
                            && event.getDate().toLocalDate().equals(date)
                            && isDateInPeriod(timePeriodInfo, event))
                    .count();
        } else if (patientName != null && !patientName.isEmpty()) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> StringUtils.containsIgnoreCase(event.getPatient().getName(), patientName)
                            && isDateInPeriod(timePeriodInfo, event))
                    .count();
        } else if (date != null) {
            events = eventDao.getFilteredEventsPage(dbPage, date, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> event.getDate().toLocalDate().equals(date)
                            && isDateInPeriod(timePeriodInfo, event))
                    .count();
        } else if (!timePeriodInfo.getEndTime().equals(LocalTime.of(23, 59))
                || !timePeriodInfo.getStartTime().equals(LocalTime.of(0, 0))) {
            events = eventDao.getFilteredEventsPage(dbPage, timePeriodInfo);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> isDateInPeriod(timePeriodInfo, event))
                    .count();
        } else {
            events = getEventsPage(dbPage);
            countOfEvents = getEvents().size();
        }
        return events;
    }

    private boolean isDateInPeriod(TimePeriodInfo timePeriodInfo, Event event) {
        return (event.getDate().toLocalTime().isBefore(timePeriodInfo.getEndTime())
                || event.getDate().toLocalTime().equals(timePeriodInfo.getEndTime()))
                && (event.getDate().toLocalTime().isAfter(timePeriodInfo.getStartTime())
                || event.getDate().toLocalTime().equals(timePeriodInfo.getStartTime()));
    }

    private List<Event> collectEvents(PrescriptionInfo prescriptionInfo, List<LocalTime> times) {
        List<String> prescriptionDays = prescriptionInfo.getPrescription().getPrescriptionDays();
        List<Event> events = new ArrayList<>();
        for (LocalTime time : times) {
            for (LocalDate date = prescriptionInfo.getPrescription().getStartDate();
                 date.isBefore(prescriptionInfo.getPrescription().getEndDate());
                 date = date.plusDays(1)) {
                if (prescriptionDays.contains(date.getDayOfWeek().name())) {
                    Event event = new Event();
                    event.setPatient(prescriptionInfo.getPrescription().getPatient());
                    event.setType(prescriptionInfo.getPrescription().getType());
                    event.setDate(LocalDateTime.of(date, time));
                    events.add(event);
                }
            }
        }
        return events;
    }

    private List<LocalTime> getPrescriptionTimes(PrescriptionInfo prescriptionInfo) {
        return prescriptionInfo.getPrescriptionTimes()
                .stream()
                .map(PrescriptionTime::getTime)
                .collect(Collectors.toList());
    }

}
