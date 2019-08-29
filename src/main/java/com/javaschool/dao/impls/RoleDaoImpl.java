package com.javaschool.dao.impls;

import com.javaschool.dao.interfaces.RoleDao;
import com.javaschool.entities.Role;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Role>) session.createQuery("from Role").list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getRole(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }
}
