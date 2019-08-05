package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.entities.Prescription;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PrescriptionDaoImpl implements PrescriptionDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prescription);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Prescription> getPrescriptions() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Prescription>) session.createQuery("from Prescription").list();

    }

    @Override
    public Prescription getPrescription(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Prescription.class, id);
    }

    @Override
    public void updatePrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.update(prescription);
    }

    @Override
    public void deletePrescription(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Prescription.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deletePrescriptions(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Prescription where patient.id = :id");
        query.setParameter("id", patientId);
        query.list().forEach(session::delete);
    }

}
