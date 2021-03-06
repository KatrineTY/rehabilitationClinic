package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PatientDao;
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
public class PatientDaoImpl implements PatientDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Patient> getPatients() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Patient>) session.createQuery("from Patient").list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.save(patient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient getPatient(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Patient where name = :name");
        query.setParameter("name", name);
        return (Patient) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient getPatient(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Patient where id = :id");
        query.setParameter("id", id);
        return (Patient) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInsuranceContains(String insurance) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Patient where insurance = :insurance");
        query.setParameter("insurance", insurance);
        return !query.list().isEmpty();

    }

}
