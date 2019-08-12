package com.javaschool.services.impls;

import com.javaschool.dao.impls.EventDaoImpl;
import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PrescriptionTime;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
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
    public int getCountOfEvents() {
        return (int) Math.ceil(((float) countOfEvents / EventDaoImpl.COUNT_OF_EVENTS_PER_PAGE));
    }

    @Override
    public List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date) {
        List<Event> events;
        int dbPage = page - 1;
        if (date != null && (patientName != null && !patientName.isEmpty())) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName, date);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> event.getPatient().getName().equals(patientName)
                            && event.getDate().toLocalDate().equals(date))
                    .count();
        } else if (patientName != null && !patientName.isEmpty()) {
            events = eventDao.getFilteredEventsPage(dbPage, patientName);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> event.getPatient().getName().equals(patientName))
                    .count();
        } else if (date != null) {
            events = eventDao.getFilteredEventsPage(dbPage, date);
            countOfEvents = (int) getEvents().stream()
                    .filter(event -> event.getDate().toLocalDate().equals(date))
                    .count();
        } else {
            events = getEventsPage(dbPage);
            countOfEvents = getEvents().size();
        }
        return events;
    }

    private List<Event> collectEvents(PrescriptionInfo prescriptionInfo, List<LocalTime> times) {
        List<Event> events = new ArrayList<>();
        for (LocalTime time : times) {
            for (LocalDate date = prescriptionInfo.getPrescription().getStartDate();
                 date.isBefore(prescriptionInfo.getPrescription().getEndDate());
                 date = date.plusDays(1)) {
                Event event = new Event();
                event.setPatient(prescriptionInfo.getPrescription().getPatient());
                event.setType(prescriptionInfo.getPrescription().getType());
                event.setDate(LocalDateTime.of(date, time));
                events.add(event);
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
