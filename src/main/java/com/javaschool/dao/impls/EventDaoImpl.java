package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
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

    @Override
    public void addEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.save(event);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient = :patient and date = :date and type = :type");
        query.setParameter("patient", event.getPatient());
        query.setParameter("date", event.getDate());
        query.setParameter("type", event.getType());
        query.list().forEach(session::delete);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteEventsByPatientId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient.id = :id");
        query.setParameter("id", id);
        query.list().forEach(session::delete);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event");
        return query.list();
    }

    @Override
    public void takeTask(int id, Employee nurse) {
        Session session = sessionFactory.getCurrentSession();
        Event event = getEventById(id, session);
        event.setNurse(nurse);
        event.setStatus("In progress");
    }

    @Override
    public void rejectTask(int id, Employee nurse, String comment) {
        Session session = sessionFactory.getCurrentSession();
        Event event = getEventById(id, session);
        event.setStatus("Rejected");
        event.setNurse(nurse);
        event.setComment(comment);
    }

    private Event getEventById(int id, Session session) {
        Query query = session.createQuery("from Event where id = :id");
        query.setParameter("id", id);
        return (Event) query.uniqueResult();
    }

}
