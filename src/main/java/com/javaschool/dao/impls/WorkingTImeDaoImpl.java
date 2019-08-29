package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.WorkingTImeDao;
import com.javaschool.entities.WorkingTime;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class WorkingTImeDaoImpl implements WorkingTImeDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<WorkingTime> getWorkingTimes() {
        Session session = sessionFactory.getCurrentSession();
        return (List<WorkingTime>) session.createQuery("from WorkingTime").list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorkingTime getWorkingTime(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(WorkingTime.class, id);
    }
}
