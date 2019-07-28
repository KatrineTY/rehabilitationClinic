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
        Session session = sessionFactory.openSession();
        session.save(patientCard);
        session.close();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<PatientCard> getPatientCards() {
        Session session = sessionFactory.openSession();
        List<PatientCard> patientCards = session.createCriteria(PatientCard.class).addOrder(Order.asc("id")).list();
        session.close();
        return patientCards;
    }

    @Override
    public PatientCard getPatientCardByPatient(Patient patient) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from PatientCard where patient = :patient");
        query.setParameter("patient", patient);
        PatientCard patientCard = (PatientCard) query.uniqueResult();
        session.close();
        return patientCard;
    }

    @Override
    public void updatePatientCard(PatientCard patientCard) {
        Session session = sessionFactory.openSession();
        session.update(patientCard);
        session.flush();
        session.close();
    }

}
