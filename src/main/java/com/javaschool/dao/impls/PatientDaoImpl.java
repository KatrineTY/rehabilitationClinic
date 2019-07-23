package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.entities.Patient;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PatientDaoImpl implements PatientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Patient> getPatients() {
        Session session = sessionFactory.openSession();
        List<Patient> patients = session.createCriteria(Patient.class).addOrder(Order.asc("id")).list();
        session.close();
        return patients;
    }

    @Override
    public void addPatient(Patient patient) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(patient);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Patient getPatientByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Patient where name = :name");
        query.setParameter("name", name);
        Patient patient = (Patient) query.uniqueResult();
        session.close();
        return patient;
    }

}
