package com.javaschool.dao.interfaces;

import com.javaschool.dao.objects.Employee;

public interface EmployeeDao {
    Employee getEmployeeByLogin(String login);

    Employee getEmployeeByName(String name);

}
