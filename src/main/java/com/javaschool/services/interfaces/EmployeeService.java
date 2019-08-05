package com.javaschool.services.interfaces;

import com.javaschool.entities.Employee;

public interface EmployeeService {

    Employee getEmployeeByLogin(String login);

    Employee getEmployeeByName(String name);

}
