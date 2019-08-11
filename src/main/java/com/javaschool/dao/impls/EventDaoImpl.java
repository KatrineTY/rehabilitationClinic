package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@NoArgsConstructor
public class EventDaoImpl implements EventDao {
    public static final int COUNT_OF_EVENTS_PER_PAGE = 5;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient = :patient");
        query.setParameter("patient", patient);
        return (List<Event>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient.id = :id");
        query.setParameter("id", patientId);
        return (List<Event>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(PrescriptionInfo prescriptionInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient = :patient and type = :type and status = 'Planned'");
        query.setParameter("patient", prescriptionInfo.getPrescription().getPatient());
        query.setParameter("type", prescriptionInfo.getPrescription().getType());
        return query.list();
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
        session.delete(event);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteEvents(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient.id = :id and status = 'Planned'");
        query.setParameter("id", patientId);
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
    public void updateEventStatus(int id, Employee nurse, String comment, String status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where id = :id");
        query.setParameter("id", id);
        Event event = (Event) query.uniqueResult();
        event.setNurse(nurse);
        event.setStatus(status);
        event.setComment(comment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEventsPage(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event order by patient.name");
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, LocalDate date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where date(date) = :date order by patient.name");
        query.setParameter("date", date);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, String patientName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient.name = :name order by patient.name");
        query.setParameter("name", patientName);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event " +
                "where patient.name = :name " +
                "and date(date) = :date " +
                "order by patient.name");
        query.setParameter("name", patientName);
        query.setParameter("date", date);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

}
