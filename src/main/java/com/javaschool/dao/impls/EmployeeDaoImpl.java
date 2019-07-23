package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.entities.Employee;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Employee getEmployeeByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee where login = :login");
        query.setParameter("login", login);
        Employee employee = (Employee) query.uniqueResult();
        session.close();
        return employee;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee where name = :name");
        query.setParameter("name", name);
        Employee employee = (Employee) query.uniqueResult();
        session.close();
        return employee;
    }

}
