package com.javaschool.services.interfaces;

import com.javaschool.entities.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Retrieve employee by his login
     *
     * @param login - the login to be used to get specific employee
     * @return the specified employee
     */
    Employee getEmployeeByLogin(String login);

    /**
     * Retrieve employee by his name
     *
     * @param name - the name to be used to get specific employee
     * @return the specified employee
     */
    Employee getEmployeeByName(String name);

    /**
     * Retrieve all attending doctors
     *
     * @return list of attending doctors
     */
    List<String> getAttendingDoctorNames();

    /**
     * Retrieve all responsible doctors
     *
     * @return list of responsible doctors
     */
    List<String> getResponsibleDoctorNames();

}
