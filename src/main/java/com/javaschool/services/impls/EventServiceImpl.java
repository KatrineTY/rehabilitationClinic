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
        List<LocalTime> times = prescriptionInfo.getPrescriptionTimes()
                .stream()
                .map(PrescriptionTime::getTime)
                .collect(Collectors.toList());
        for (LocalTime time : times) {
            Event event = new Event();
            event.setPatient(prescriptionInfo.getPrescription().getPatient());
            event.setType(prescriptionInfo.getPrescription().getType());
            for (LocalDate date = prescriptionInfo.getPrescription().getStartDate();
                 date.isBefore(prescriptionInfo.getPrescription().getEndDate());
                 date = date.plusDays(1)) {
                event.setDate(LocalDateTime.of(date, time));
                eventDao.addEvent(event);

            }
        }
    }

}
