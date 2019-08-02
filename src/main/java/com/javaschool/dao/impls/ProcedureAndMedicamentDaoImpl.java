package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.ProcedureAndMedicamentDao;
import com.javaschool.entities.ProcedureAndMedicament;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProcedureAndMedicamentDaoImpl implements ProcedureAndMedicamentDao {
    @Autowired
    SessionFactory sessionFactory;

    public ProcedureAndMedicament getElementWithId(ProcedureAndMedicament procedureAndMedicament) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcedureAndMedicament where name = :name and kind = :kind");
        query.setParameter("name", procedureAndMedicament.getName());
        query.setParameter("kind", procedureAndMedicament.getKind());
        return (ProcedureAndMedicament) query.uniqueResult();
    }

}
