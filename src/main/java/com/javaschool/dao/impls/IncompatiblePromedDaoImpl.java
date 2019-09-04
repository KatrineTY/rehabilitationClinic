package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.IncompatiblePromedDao;
import com.javaschool.entities.IncompatibleProcedureAndMedicament;
import com.javaschool.entities.ProcedureAndMedicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncompatiblePromedDaoImpl implements IncompatiblePromedDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<IncompatibleProcedureAndMedicament> getIncompatiblePromeds(ProcedureAndMedicament promed) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from IncompatibleProcedureAndMedicament where promed =: promed or incompatiblePromed = :promed");
        query.setParameter("promed", promed);
        return query.list();
    }
}
