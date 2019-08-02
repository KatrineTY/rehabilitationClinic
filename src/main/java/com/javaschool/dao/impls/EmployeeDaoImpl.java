package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.entities.Employee;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Employee getEmployeeByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employee where login = :login");
        query.setParameter("login", login);
        return (Employee) query.uniqueResult();
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employee where name = :name");
        query.setParameter("name", name);
        return (Employee) query.uniqueResult();
    }

}
