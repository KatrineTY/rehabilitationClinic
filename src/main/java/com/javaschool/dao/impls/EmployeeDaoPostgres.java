package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EmployeeDao;
import com.javaschool.dao.objects.Employee;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmployeeDaoPostgres implements EmployeeDao {

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

}
