package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDaoImpl implements EventDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEventsByPatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient = :patient");
        query.setParameter("patient", patient);
        return (List<Event>) query.list();
    }

}
