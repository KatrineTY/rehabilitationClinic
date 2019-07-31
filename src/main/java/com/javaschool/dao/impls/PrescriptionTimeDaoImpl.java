package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionTimeDao;
import com.javaschool.entities.PrescriptionTime;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
