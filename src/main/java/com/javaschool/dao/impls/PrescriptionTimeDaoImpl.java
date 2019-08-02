package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionTimeDao;
import com.javaschool.entities.Prescription;
import com.javaschool.entities.PrescriptionTime;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PrescriptionTimeDaoImpl implements PrescriptionTimeDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPrescriptionTime(PrescriptionTime prescriptionTime) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prescriptionTime);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PrescriptionTime> getPrescriptionTimesByPrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PrescriptionTime where prescription = :prescription");
        query.setParameter("prescription", prescription);
        return query.list();
    }

    @Override
    public void updatePrescriptionTimes(List<PrescriptionTime> prescriptionTimes) {
        Session session = sessionFactory.getCurrentSession();
        prescriptionTimes.forEach(session::merge);
    }

    @Override
    public void deletePrescriptionTimesByPrescriptionId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete PrescriptionTime where prescription.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
