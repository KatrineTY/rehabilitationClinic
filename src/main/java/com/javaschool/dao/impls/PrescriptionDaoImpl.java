package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.entities.Prescription;
import com.javaschool.entities.ProcedureAndMedicament;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class PrescriptionDaoImpl implements PrescriptionDao {
    @Autowired
    SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Prescription> getPrescriptions() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Prescription>) session.createQuery("from Prescription").list();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prescription getPrescription(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Prescription.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePrescription(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(prescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePrescription(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Prescription.class, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void deletePrescriptions(int patientId) {
        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("delete from Prescription where patient.id = :id");
        Query query = session.createQuery("from Prescription where patient.id = :id");
        query.setParameter("id", patientId);
        query.list().forEach(session::delete);
//        query.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prescription getLastPrescription(String patientName, ProcedureAndMedicament promed) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Prescription " +
                "where patient.name =: patientName and type.name =: name and type.kind =: kind " +
                "order by endDate");
        query.setParameter("patientName", patientName);
        query.setParameter("name", promed.getName());
        query.setParameter("kind", promed.getKind());
        if (query.list().isEmpty()) {
            return null;
        } else {
            return (Prescription) query.list().get(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Prescription> getPrescriptions(String patientName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Prescription where patient.name =: patientName");
        query.setParameter("patientName", patientName);
        return query.list();
    }

}
