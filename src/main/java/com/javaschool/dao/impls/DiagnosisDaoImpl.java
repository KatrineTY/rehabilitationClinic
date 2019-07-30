package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.DiagnosisDao;
import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class DiagnosisDaoImpl implements DiagnosisDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDiagnosis(Diagnosis diagnosis) {
        Session session = sessionFactory.getCurrentSession();
        session.save(diagnosis);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Diagnosis> getDiagnosesByCard(PatientCard patientCard) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Diagnosis where patient_card = :patientCard");
        query.setParameter("patientCard", patientCard);
        return (List<Diagnosis>) query.list();
    }

    @Override
    public void updateDiagnosis(Diagnosis diagnosis) {
        Session session = sessionFactory.getCurrentSession();
        session.update(diagnosis);
    }

}
