package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.entities.Event;
import com.javaschool.services.interfaces.ScheduleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private EventDao eventDao;
    @Autowired
    private PatientDao patientDao;

    public List<Event> getScheduleByPatientId(int id) {
        return eventDao.getEventsByPatient(patientDao.getPatientById(id));
    }

}
