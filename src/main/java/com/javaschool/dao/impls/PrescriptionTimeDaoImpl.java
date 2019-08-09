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
    public List<PrescriptionTime> getPrescriptionTimes(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PrescriptionTime where prescription = :prescription");
        query.setParameter("prescription", prescription);
        return query.list();
    }

    @Override
    public void updatePrescriptionTime(PrescriptionTime prescriptionTime) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(prescriptionTime);
    }

}
