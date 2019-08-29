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
     * Change account status of an employee
     *
     * @param id      - id of the specified employee
     * @param enabled - new status
     */
    void changeAccountStatusOfEmployee(int id, boolean enabled);

    /**
     * Retrieve employee by id
     *
     * @param id - the specified id
     * @return the specified employee
     */
    Employee getEmployee(int id);

}
