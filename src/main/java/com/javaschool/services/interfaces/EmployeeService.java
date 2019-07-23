package com.javaschool.services.interfaces;

import com.javaschool.entities.Employee;

public interface EmployeeService {

    boolean checkEmployee(Employee employee);

    Employee getEmployeeByLogin(String login);

}
