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
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeByLogin(String login) {
        return employeeDao.getEmployeeByLogin(login);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeByName(String name) {
        return employeeDao.getEmployeeByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAttendingDoctorNames() {
        return employeeDao.getEmployeeWithRoleNames("ROLE_MAIN_DOCTOR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getResponsibleDoctorNames() {
        return employeeDao.getEmployeeWithRoleNames("ROLE_DOCTOR");
    }

}
