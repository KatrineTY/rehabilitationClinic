package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.entities.Prescription;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<Prescription> getAllPrescriptions() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Prescription>) session.createQuery("from Prescription").list();

    }

    @Override
    public Prescription getPrescriptionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Prescription.class, id);
    }

    @Override
    public void updatePrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(prescription);
    }

}
