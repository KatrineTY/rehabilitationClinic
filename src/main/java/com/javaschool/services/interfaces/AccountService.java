package com.javaschool.services.interfaces;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Event;
import com.javaschool.entities.PatientCard;

import java.util.List;

public interface AccountService {

    List<PatientCard> getPatientCards();

    String getEmployeeNameByLogin(String login);

    List<PrescriptionInfo> getPrescriptions();

    List<Event> getEvents();

}
