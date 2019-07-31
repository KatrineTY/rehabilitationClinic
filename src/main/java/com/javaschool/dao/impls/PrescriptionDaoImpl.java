package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.entities.Prescription;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
