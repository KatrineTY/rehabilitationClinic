package com.javaschool.services.impls;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
public class AccountServiceIImpl implements AccountService {
    @Autowired
    private PatientCardService patientCardService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private EventService eventService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<PatientCard> getPatientCards() {
        return patientCardService.getPatientCards();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public String getEmployeeNameByLogin(String login) {
        return employeeService.getEmployeeByLogin(login).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionInfo> getPrescriptions() {
        return prescriptionService.getPrescriptions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

}
