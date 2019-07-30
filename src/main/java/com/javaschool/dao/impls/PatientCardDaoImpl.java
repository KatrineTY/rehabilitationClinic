package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PatientCardDao;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
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
public class PatientCardDaoImpl implements PatientCardDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPatientCard(PatientCard patientCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(patientCard);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<PatientCard> getPatientCards() {
        Session session = sessionFactory.getCurrentSession();
        return (List<PatientCard>) session.createCriteria(PatientCard.class).addOrder(Order.asc("id")).list();
    }

    @Override
    public PatientCard getPatientCardByPatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PatientCard where patient = :patient");
        query.setParameter("patient", patient);
        return (PatientCard) query.uniqueResult();
    }

    @Override
    public void updatePatientCard(PatientCard patientCard) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patientCard);
    }

}
