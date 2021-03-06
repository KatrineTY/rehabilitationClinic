package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Event;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@Slf4j
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
        Query query = session.createQuery("from Event where patient.id = :id and date > now()");
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
        Query query = session.createQuery("from Event where date > now()");
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEventStatus(int id, Employee nurse, String comment, String status, LocalTime startTaskTime, LocalTime endTaskTime) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where id = :id");
        query.setParameter("id", id);
        Event event = (Event) query.uniqueResult();
        event.setNurse(nurse);
        event.setStatus(status);
        event.setComment(comment);
        if (event.getStartTaskTime() == null) {
            event.setStartTaskTime(startTaskTime);
        }
        event.setEndTaskTime(endTaskTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEventsPage(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where date > now() order by patient.name");
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
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEventsPerDay() {
        Session session = sessionFactory.getCurrentSession();
        ZonedDateTime startDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        ZonedDateTime endDate = startDate.plusDays(1);
        log.info("Get events between Start: {} end: {}", startDate, endDate);
        Query query = session.createQuery("from Event " +
                "where date between :startDate and :endDate");
        query.setParameter("startDate", startDate.toLocalDateTime());
        query.setParameter("endDate", endDate.toLocalDateTime());

        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(String nurseName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where nurse.name = :nurseName and status='In progress'");
        query.setParameter("nurseName", nurseName);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getNearestMedicinesEvents() {
        Session session = sessionFactory.getCurrentSession();
        LocalTime endTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).toLocalTime();
        Query query = session.createQuery("from Event " +
                "where startTaskTime between :startTime and :endTime and type.kind='Medicament'");
        query.setParameter("startTime", endTime.minusMinutes(10));
        query.setParameter("endTime", endTime.minusMinutes(5));
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getNearestProceduresEvents() {
        Session session = sessionFactory.getCurrentSession();
        LocalTime endTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).toLocalTime();
        Query query = session.createQuery("from Event " +
                "where startTaskTime between :startTime and :endTime and type.kind='Procedure'");
        query.setParameter("startTime", endTime.minusMinutes(60));
        query.setParameter("endTime", endTime.minusMinutes(30));
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Event> getEvents(String patientName, LocalDate startDate, LocalDate endDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Event where " +
                "status!='Planned' and status!='In progress' " +
                "and patient.name = :patientName " +
                "and date(date) between :startDate and :endDate");
        query.setParameter("patientName", patientName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    @Override
    public Event getEvent(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Event.class, id);
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
