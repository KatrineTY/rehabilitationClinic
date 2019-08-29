package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.entities.Employee;
import com.javaschool.services.interfaces.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeByLogin(String login) {
        return employeeDao.getEmployeeByLogin(login);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeByName(String name) {
        return employeeDao.getEmployeeByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getAttendingDoctorNames() {
        return employeeDao.getEmployeeWithRoleNames("ROLE_MAIN_DOCTOR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getResponsibleDoctorNames() {
        return employeeDao.getEmployeeWithRoleNames("ROLE_DOCTOR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void disableEmployee(int id) {
        employeeDao.changeAccountStatusOfEmployee(id, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void changeAccountStatusOfEmployee(int id, boolean enabled) {
        employeeDao.changeAccountStatusOfEmployee(id, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(int id) {
        return employeeDao.getEmployee(id);
    }

}
