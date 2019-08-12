package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PatientCardDao;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        return (List<PatientCard>) session.createQuery("from PatientCard").list();
    }

    @Override
    public PatientCard getPatientCard(Patient patient) {
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

    @Override
    public void changeStatus(int patientId, String status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PatientCard where patient.id =: patientId");
        query.setParameter("patientId", patientId);
        PatientCard patientCard = (PatientCard) query.uniqueResult();
        patientCard.setStatus(status);
        session.update(patientCard);
    }

    @Override
    public boolean isFreeBedInTheWard(String building, int ward) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PatientCard where building = :building and ward = :ward");
        query.setParameter("building", building);
        query.setParameter("ward", ward);
        return query.list().size() < 4;

    }

}
