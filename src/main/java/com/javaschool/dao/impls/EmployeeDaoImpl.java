package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.entities.Employee;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employee where login = :login");
        query.setParameter("login", login);
        return (Employee) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employee where name = :name");
        query.setParameter("name", name);
        return (Employee) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getEmployeeWithRoleNames(String role) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select name from Employee where role.name = :role");
        query.setParameter("role", role);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Employee>) session.createQuery("from Employee").list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeAccountStatusOfEmployee(int id, boolean enabled) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        employee.setEnabled(enabled);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

}
