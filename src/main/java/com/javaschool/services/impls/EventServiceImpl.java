package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PrescriptionTime;
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
    @Autowired
    private EventDao eventDao;

    @Override
    public void addEventsForPrescription(PrescriptionInfo prescriptionInfo) {
        List<LocalTime> times = getPrescriptionTimes(prescriptionInfo);
        List<Event> events = collectEvents(prescriptionInfo, times);
        events.forEach(event -> eventDao.addEvent(event));
    }

    @Override
    public void deleteEventsForPrescription(PrescriptionInfo prescriptionInfo) {
        List<LocalTime> times = getPrescriptionTimes(prescriptionInfo);
        List<Event> events = collectEvents(prescriptionInfo, times);
        events.forEach(event -> eventDao.deleteEvent(event));
    }

    @Override
    public void deleteEventsByPatientId(int id) {
        eventDao.deleteEventsByPatientId(id);
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
