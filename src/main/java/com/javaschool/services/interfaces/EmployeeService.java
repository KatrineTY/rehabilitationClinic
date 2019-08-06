package com.javaschool.services.interfaces;

import com.javaschool.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByLogin(String login);

    Employee getEmployeeByName(String name);

    List<String> getAttendingDoctorNames();

    List<String> getResponsibleDoctorNames();

}
