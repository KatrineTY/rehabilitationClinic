package com.javaschool.services.interfaces;

import com.javaschool.entities.Employee;
import com.javaschool.entities.Role;
import com.javaschool.entities.WorkingTime;

import java.util.List;

public interface AdminService {

    /**
     * Add an employee into DB
     *
     * @param employee - the specified employee
     */
    void addEmployee(Employee employee);

    /**
     * Update an employee
     *
     * @param employee - the specified employee
     */
    void updateEmployee(Employee employee);

    /**
     * Retrieve roles
     *
     * @return list of role names
     */
    List<Role> getRoles();

    /**
     * Retrieve working times
     *
     * @return list of working times
     */
    List<WorkingTime> getWorkingTimes();

    /**
     * Retrieve employees
     *
     * @return list of employees
     */
    List<Employee> getEmployees();

    /**
     * Disable employee account
     *
     * @param id - id of the specified employee
     */
    void disableEmployee(int id);

    /**
     * Retrieve employee by id
     *
     * @param id - the specified id
     * @return the specified employee
     */
    Employee getEmployee(int id);
}
