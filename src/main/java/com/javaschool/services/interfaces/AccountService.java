package com.javaschool.services.interfaces;

import com.javaschool.entities.PatientCard;

import java.util.List;

public interface AccountService {

    List<PatientCard> getPatientCards();

    String getEmployeeNameByLogin(String login);

}
