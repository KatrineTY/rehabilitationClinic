package com.javaschool.dao.interfaces;

import com.javaschool.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee getEmployeeByLogin(String login);

    Employee getEmployeeByName(String name);

    List<String> getEmployeeWithRoleNames(String role);

}
