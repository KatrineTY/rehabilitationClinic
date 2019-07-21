package com.javaschool.services.interfaces;

import com.javaschool.dao.objects.Employee;

public interface LoginService {

    boolean checkEmployee(Employee employee);

    Employee getEmployeeByLogin(String login);

}
