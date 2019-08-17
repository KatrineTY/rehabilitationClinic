package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
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

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where patient.id = :id");
        query.setParameter("id", patientId);
        return (List<Event>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.save(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void deleteEvents(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Event where patient.id = :id and status = 'Planned'");
        query.setParameter("id", patientId);
        query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEvents(PrescriptionInfo prescriptionInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Event where patient = :patient and type = :type and status = 'Planned'");
        query.setParameter("patient", prescriptionInfo.getPrescription().getPatient());
        query.setParameter("type", prescriptionInfo.getPrescription().getType());
        query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event");
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEventsPage(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event order by patient.name");
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, LocalDate date, TimePeriodInfo timePeriodInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event " +
                "where date(date) = :date " +
                "and cast(date as time) between cast(:startTime as time) and cast(:endTime as time) " +
                "order by patient.name");
        query.setParameter("date", date);
        setPeriodTime(timePeriodInfo, query);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, String patientName, TimePeriodInfo timePeriodInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event " +
                "where lower(patient.name) like '%' || lower(:name) || '%' " +
                "and cast(date as time) between cast(:startTime as time) and cast(:endTime as time) " +
                "order by patient.name");
        query.setParameter("name", patientName);
        setPeriodTime(timePeriodInfo, query);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, String patientName, LocalDate date, TimePeriodInfo timePeriodInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event " +
                "where lower(patient.name) like '%' || lower(:name) || '%' " +
                "and date(date) = :date " +
                "and cast(date as time) between cast(:startTime as time) and cast(:endTime as time) " +
                "order by patient.name");
        query.setParameter("name", patientName);
        query.setParameter("date", date);
        setPeriodTime(timePeriodInfo, query);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getFilteredEventsPage(int page, TimePeriodInfo timePeriodInfo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event " +
                "where cast(date as time) between cast(:startTime as time) and cast(:endTime as time) " +
                "order by patient.name");
        setPeriodTime(timePeriodInfo, query);
        query.setFirstResult(page * COUNT_OF_EVENTS_PER_PAGE)
                .setMaxResults(COUNT_OF_EVENTS_PER_PAGE);
        return query.list();
    }

    /**
     * Set query start and end times
     *
     * @param timePeriodInfo - time period that contains start and end times
     * @param query          - the modifica
     */
    private void setPeriodTime(TimePeriodInfo timePeriodInfo, Query query) {
        query.setParameter("startTime", timePeriodInfo.getStartTime());
        query.setParameter("endTime", timePeriodInfo.getEndTime());
    }

}
