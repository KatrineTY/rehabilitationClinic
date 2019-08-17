package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.DiagnosisDao;
import com.javaschool.entities.Diagnosis;
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
public class DiagnosisDaoImpl implements DiagnosisDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDiagnosis(Diagnosis diagnosis) {
        Session session = sessionFactory.getCurrentSession();
        session.save(diagnosis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Diagnosis> getDiagnoses(PatientCard patientCard) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Diagnosis where patientCard = :patientCard");
        query.setParameter("patientCard", patientCard);
        return (List<Diagnosis>) query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdateDiagnosis(Diagnosis diagnosis) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(diagnosis);
    }

}
