package com.javaschool.services.impls;

import com.javaschool.entities.Employee;
import com.javaschool.entities.Role;
import com.javaschool.entities.WorkingTime;
import com.javaschool.services.interfaces.AdminService;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.RoleService;
import com.javaschool.services.interfaces.WorkingTimeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private WorkingTimeService workingTimeService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        fillEmployee(employee);
        employee.setPassword(encodePassword(employee.getPassword()));
        employeeService.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee.getPassword().isEmpty()) {
            employee.setPassword(employeeService.getEmployee(employee.getId()).getPassword());
        } else {
            employee.setPassword(encodePassword(employee.getPassword()));
        }
        employee.setId(employeeService.getEmployeeByName(employee.getName()).getId());
        fillEmployee(employee);
        employeeService.updateEmployee(employee);
    }

    /**
     * Fill additional employee info
     *
     * @param employee - the employee to be filled
     */
    private void fillEmployee(Employee employee) {
        employee.setRole(roleService.getRole(employee.getRole().getId()));
        employee.setWorkingTime(workingTimeService.getWorkingTime(employee.getWorkingTime().getId()));
        employee.setLogin(createLogin(employee.getName()));
    }

    /**
     * Encode given password
     *
     * @param password - the specified password
     * @return encoded password
     */
    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * Create login from employee name
     *
     * @param name - the specified name
     * @return new login
     */
    private String createLogin(String name) {
        return name.trim().toLowerCase().replace(" ", ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkingTime> getWorkingTimes() {
        return workingTimeService.getWorkingTimes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void disableEmployee(int id) {
        employeeService.disableEmployee(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(int id) {
        return employeeService.getEmployee(id);
    }

}
