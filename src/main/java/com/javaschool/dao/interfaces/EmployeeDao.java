package com.javaschool.dao.interfaces;

import com.javaschool.entities.Employee;

import java.util.List;

public interface EmployeeDao {

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
     * Retrieve employees by role name
     *
     * @param role - the role to be used to get specific employee
     * @return list of employees
     */
    List<String> getEmployeeWithRoleNames(String role);

}
