package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.entities.Employee;
import com.javaschool.services.interfaces.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean checkEmployee(Employee employee) {
        Employee employeeDB = employeeDao.getEmployeeByLogin(employee.getLogin());
        return employeeDB != null && employeeDB.getPassword().equals(employee.getPassword());
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        return employeeDao.getEmployeeByLogin(login);
    }


}
